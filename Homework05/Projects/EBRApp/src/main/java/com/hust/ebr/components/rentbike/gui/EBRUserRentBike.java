package com.hust.ebr.components.rentbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;
import com.hust.ebr.components.returnbike.controller.EBRUserReturnBikeController;
import com.hust.ebr.components.returnbike.gui.EBRUserReturnBike;
import com.hust.ebr.utils.CostCalculator;

import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class EBRUserRentBike extends JFrame{
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
    private JLabel labelCost;
    private JLabel labelStatus;
    private JLabel labelFromDocking;
    private JButton buttonReturnBike;
    private JLabel labelCardBalance;
    private JLabel labelMaxAmountRenting;

    private CreditCard creditCard;
    private Bike bike;
    private ZonedDateTime timeBegin;
    private double maxTimeRent;
    public EBRUserRentBike(EBRUserRentBikeController controller, Bike bike, CreditCard creditCard) {
        this.bike = bike;
        this.creditCard = creditCard;
        this.timeBegin = ZonedDateTime.now();
        CostCalculator cal = new CostCalculator();
        maxTimeRent = cal.maxTimeRenting(bike,creditCard);
        setDisplayLayOut();
    }

    public void setDisplayLayOut(){
        displayData();
        rootDialog = new JDialog();
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(17,1));
        rootPanel.add(new JLabel("USER DETAIL", SwingConstants.CENTER));
        rootPanel.add(labelCardOwner);
        rootPanel.add(labelCardNumber);
        rootPanel.add(labelCardBalance);
        rootPanel.add(labelMaxAmountRenting);
        rootPanel.add(labelCurrentDate);
        rootPanel.add(new JLabel("BIKE DETAIL", SwingConstants.CENTER));
        rootPanel.add(labelId);
        rootPanel.add(labelName);
        rootPanel.add(labelWeight);
        rootPanel.add(labelLicensePlate);
        rootPanel.add(labelManufactureDate);
        rootPanel.add(labelProducer);
        rootPanel.add(labelCost);
        rootPanel.add(labelStatus);
        rootPanel.add(labelFromDocking);
        rootPanel.add(buttonReturnBike);
        handleButtonEvent();
        rootDialog.setTitle("Rent Bike Detail");
        rootDialog.setContentPane(rootPanel);
        rootDialog.setSize(600, 500);
        rootDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        rootDialog.setVisible(true);
        rootDialog.setLocationRelativeTo(null);
        rootDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    }

    public void displayData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a z");
        if (bike != null) {
            labelId.setText("ID: " + bike.getId());
            labelName.setText("Name: " + bike.getName());
            labelWeight.setText("Weight: " + bike.getWeight());
            labelLicensePlate.setText("License plate: " + bike.getLicensePlate());
            labelManufactureDate.setText("Manufacture date: " + bike.getManufacturingDate());
            labelProducer.setText("Producer: " + bike.getProducer());
            labelCost.setText("Deposit Cost: " + bike.getCost());
            labelStatus.setText("Status: " + bike.getStatus());
            labelFromDocking.setText("From docking: " + bike.getDockingStationId());
        }

        if (creditCard != null) {
            labelCardOwner.setText("Card owner: " + creditCard.getCardOwner());
            labelCardNumber.setText("Card number: " + creditCard.getCardNumber());
            labelCardBalance.setText(("Card balance: " + creditCard.getBalance()));
            labelMaxAmountRenting.setText("You can rent this bike for maximum: " +(int) (maxTimeRent/60)+ " hour(s) "+ (int) (maxTimeRent%60) + " minute(s)!");
        }
        labelCurrentDate.setText("Current Date: " + formatter.format(timeBegin));
    }

    private void handleButtonEvent() {
        buttonReturnBike.addActionListener(e -> {
            // TODO
            new EBRUserReturnBike(new EBRUserReturnBikeController(), bike, creditCard, timeBegin);
            rootDialog.dispose();
        });
    }
//    private void handleButtonEvent() {
//        buttonReturnBike.setOnClickListener
//    }
}
