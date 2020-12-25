package com.hust.ebr.components.returnbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.beans.EBike;
import com.hust.ebr.beans.NormalBike;
import com.hust.ebr.components.returnbike.controller.EBRUserReturnBikeController;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.utils.costCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRUserReturnBike extends JFrame {
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
    private ZonedDateTime timeReturn;
    private ZonedDateTime timeBegin;
    private String currentStationId;
    private double totalCost;
    private double depositCost;
    public EBRUserReturnBike(EBRUserReturnBikeController controller, Bike bike, CreditCard creditCard, ZonedDateTime timeBegin ) {
        this.bike = bike;
        if(bike instanceof NormalBike)
            depositCost = 400000;
        else if(bike instanceof EBike)
            depositCost = 700000;
        else
            depositCost = 550000;
        this.creditCard = creditCard;
        this.timeReturn = ZonedDateTime.now();
        this.timeBegin = timeBegin;
        costCalculator cal = new costCalculator();
        totalCost = cal.costCaculator(bike, timeBegin, timeReturn);
        stationIdSelectionBox.addItem("ds1");
        stationIdSelectionBox.addItem("ds2");
        stationIdSelectionBox.addItem("ds3");
        stationIdSelectionBox.setBounds(90, 85, 245, 31);
        setTitle("Rental Detail");
        setContentPane(rootPanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        displayData();
        handleButtonEvent();
    }

    public void displayData(){
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma z");
        if (bike!=null){
            labelId.setText("ID: " + bike.getId());
            labelName.setText("Name: " + bike.getName());
            labelWeight.setText("Weight: " + bike.getWeight());
            labelLicensePlate.setText("License plate: " + bike.getLicensePlate());
            labelCurrentDate.setText(customFormatter.format(ZonedDateTime.now()));
            labelManufactureDate.setText("Manufacture date: " + bike.getManufacturingDate());
            labelProducer.setText("Producer: " + bike.getProducer());
            labelDepositCost.setText("Deposit Cost: " + bike.getCost());
            labelTotalCost.setText("Total Cost: " + totalCost);
            labelStatus.setText("Status: " + bike.getStatus());
            labelFromDocking.setText("From docking: " + bike.getDockingStationId());
//            labelToDocking.setText("To Docking: " + (String)stationIdSelectionBox.getSelectedItem());
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
            this.dispose();
        });
    }

}