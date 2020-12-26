package com.hust.ebr.components.returnbike.gui;

import com.hust.ebr.beans.*;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.components.returnbike.controller.EBRUserReturnBikeController;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.RentalApi;
import com.hust.ebr.utils.CostCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class EBRUserReturnBike extends JFrame {
    private JDialog rootDialog;
    private JPanel rootPanel;
    private JLabel labelCardOwner;
    private JLabel labelCardNumber;
    private JLabel labelCurrentDate;
    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelWeight;
    private JLabel labelLicensePlate;
    private JLabel labelManufactureDate;
    private JLabel labelProducer;
    private JLabel labelDepositCost;
    private JLabel labelStatus;
    private JLabel labelFromDocking;
    private JLabel labelTotalCost;
    private JLabel labelRentStartTime;
    private JLabel labelRentReturnTime;
    private JButton PAYANDRETURNButton;
    private JComboBox<String> stationIdSelectionBox;
    private JLabel labelToDocking;

    private CreditCard creditCard;
    private Bike bike;
    private RentalApi rentalApi;
    private Rental rental;
    private ZonedDateTime timeReturn;
    private ZonedDateTime timeBegin;
    private String currentStationId;
    private double totalCost;
    private double depositCost;
    public EBRUserReturnBike(EBRUserReturnBikeController controller, Bike bike, CreditCard creditCard, ZonedDateTime timeBegin ) {
        this.bike = bike;
        this.depositCost = bike.getCost();
        this.creditCard = creditCard;
        this.timeReturn = ZonedDateTime.now();
        this.timeBegin = timeBegin;
        this.currentStationId = bike.getDockingStationId();
        CostCalculator cal = new CostCalculator();
        totalCost = cal.calculateRentalFee(bike, timeBegin, timeReturn);
//        totalCost = cal.rentingCost24h(timeBegin, timeReturn);
        setDisplayLayOut();
    }

    public void setDisplayLayOut(){
        displayData();
        rootDialog = new JDialog();
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(21,1));
        rootPanel.add(new JLabel("USER DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelCardOwner);
        rootPanel.add(labelCardNumber);
        rootPanel.add(labelCurrentDate);
        rootPanel.add(new JLabel("BIKE DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelId);
        rootPanel.add(labelName);
        rootPanel.add(labelWeight);
        rootPanel.add(labelLicensePlate);
        rootPanel.add(labelManufactureDate);
        rootPanel.add(labelStatus);
        rootPanel.add(labelProducer);
        rootPanel.add(labelFromDocking);
        rootPanel.add(labelToDocking);
        rootPanel.add(stationIdSelectionBox);
        stationIdSelectionBox.addItem("ds1");
        stationIdSelectionBox.addItem("ds2");
        stationIdSelectionBox.addItem("ds3");
        stationIdSelectionBox.setBounds(90, 85, 245, 31);
        handleButtonEvent();
        rootPanel.add(new JLabel("RENTAL DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelRentStartTime);
        rootPanel.add(labelRentReturnTime);
        rootPanel.add(labelDepositCost);
        rootPanel.add(labelTotalCost);
        rootPanel.add(PAYANDRETURNButton);
        handleButtonEvent();
        rootDialog.setTitle("Rental Detail");
        rootDialog.setContentPane(rootPanel);
        rootDialog.setSize(600, 500);
        rootDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        rootDialog.setVisible(true);
        rootDialog.setLocationRelativeTo(null);
        rootDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    }

    public void displayData(){
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma z");
        if (bike!=null){
            labelId.setText("ID: " + bike.getId());
            labelName.setText("Name: " + bike.getName());
            labelWeight.setText("Weight: " + bike.getWeight());
            labelLicensePlate.setText("License plate: " + bike.getLicensePlate());
            labelCurrentDate.setText("Current Date: " + customFormatter.format(ZonedDateTime.now()));
            labelManufactureDate.setText("Manufacture date: " + bike.getManufacturingDate());
            labelProducer.setText("Producer: " + bike.getProducer());
            labelDepositCost.setText("Deposit Cost: " + bike.getCost());
            labelTotalCost.setText("Total Cost: " + totalCost);
            labelStatus.setText("Status: " + bike.getStatus());
            labelFromDocking.setText("From docking: " + bike.getDockingStationId());
            labelToDocking.setText("To Docking: " + currentStationId);
        }
        if (creditCard != null) {
            labelCardOwner.setText("Card owner: " + creditCard.getCardOwner());
            labelCardNumber.setText("Card number: " + creditCard.getCardNumber());
        }
        labelRentReturnTime.setText("Return Time: " + customFormatter.format(timeReturn));
        labelRentStartTime.setText("Rent Time: " + customFormatter.format(timeBegin));
    }

    private void handleButtonEvent(){
        stationIdSelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStationId = (String)stationIdSelectionBox.getSelectedItem();
                labelToDocking.setText("To Docking: " + currentStationId);
            }
        });
        PAYANDRETURNButton.addActionListener(e -> {
            // TODO
            BankingApi bankingApi = new BankingApi();
            creditCard = bankingApi.requestCreditCard(RequestType.Refund, creditCard.getCardNumber(), depositCost);
            creditCard = bankingApi.requestCreditCard(RequestType.Deduct, creditCard.getCardNumber(), totalCost);
            creditCard = bankingApi.updateCreditCard(creditCard.getCardNumber(), false);
            bike.setStatus(Bike.Status.Available);
            bike.setDockingStationId(currentStationId);
            bike = new BikeApi().updateBike(bike);
            rental = new Rental();
            rentalApi = new RentalApi();
            rental.setBikeId(bike.getId());
            rental.setRentalDate(Date.from(timeBegin.toInstant()));
            if(bike instanceof NormalBike)
                rental.setBikeType(Rental.Type.NormalBike);
            else if(bike instanceof EBike)
                rental.setBikeType(Rental.Type.EBike);
            else
                rental.setBikeType(Rental.Type.TwinBike);
            rental.setCardNumber(creditCard.getCardNumber());
            rental.setCardOwner(creditCard.getCardOwner());
            rental.setFromStationId(bike.getDockingStationId());
            rental.setToStationId(currentStationId);
            rental.setTotalMoney(totalCost);
            rental.setTotalTime(ChronoUnit.MINUTES.between(timeBegin, timeReturn));
            rental = rentalApi.saveNewRental(rental);
            rootDialog.dispose();
        });
    }

}