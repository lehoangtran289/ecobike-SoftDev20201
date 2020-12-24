package com.hust.ebr.components.rentbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentBikePage extends ADataSinglePane<Bike> {

    private CreditCard creditCard;
    private Bike bike;
    private long timeBegin;

    // Bike attributes
    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelWeight;
    private JLabel labelLicensePlate;
    private JLabel labelManufactureDate;
    private JLabel labelProducer;
    private JLabel labelCost;
    private JLabel labelStatus;
    private JLabel labelFromDocking;

    // User attributes
    private JLabel labelCardOwner;
    private JLabel labelCardNumber;
    private JLabel labelCurrentDate;

    private JButton buttonReturnBike;

    public RentBikePage(Bike bike, CreditCard creditCard) {
        this.bike = bike;
        this.creditCard = creditCard;
        timeBegin = System.currentTimeMillis();
        buildControls();
        displayData();
        handleButtonEvent();
    }

    @Override
    public void buildControls() {
        super.buildControls();
        initLabel();
    }

    private void initLabel() {
        labelId = new JLabel();
        labelName = new JLabel();
        labelWeight = new JLabel();
        labelLicensePlate = new JLabel();
        labelManufactureDate = new JLabel();
        labelProducer = new JLabel();
        labelCost = new JLabel();
        labelStatus = new JLabel();
        labelFromDocking = new JLabel();
        labelCardNumber = new JLabel();
        labelCardOwner = new JLabel();
        labelCurrentDate = new JLabel();
        buttonReturnBike = new JButton("RETURN BIKE");

        JLabel label = new JLabel("***User***\n");
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);

        addNewLabel(labelCardOwner);
        addNewLabel(labelCardNumber);
        addNewLabel(labelCurrentDate);

        label = new JLabel("\n***Bike***\n");
        row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);

        addNewLabel(labelId);
        addNewLabel(labelName);
        addNewLabel(labelWeight);
        addNewLabel(labelLicensePlate);
        addNewLabel(labelManufactureDate);
        addNewLabel(labelProducer);
        addNewLabel(labelCost);
        addNewLabel(labelStatus);
        addNewLabel(labelFromDocking);

        row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(buttonReturnBike, c);
    }

    private void addNewLabel(JLabel label) {
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);
    }

    @Override
    public void displayData() {
        if (bike != null) {
            labelId.setText("ID: " + bike.getId());
            labelName.setText("Name: " + bike.getName());
            labelWeight.setText("Weight: " + bike.getWeight());
            labelLicensePlate.setText("License plate: " + bike.getLicensePlate());
            labelManufactureDate.setText("Manufacture date: " + bike.getManufacturingDate());
            labelProducer.setText("Producer: " + bike.getProducer());
            labelCost.setText("Cost: " + bike.getCost());
            labelStatus.setText("Status: " + bike.getStatus());
            labelFromDocking.setText("From docking: " + bike.getDockingStationId());
        }

        if (creditCard != null) {
            labelCardOwner.setText("Card owner: " + creditCard.getCardOwner());
            labelCardNumber.setText("Card number: " + creditCard.getCardNumber());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        labelCurrentDate.setText(simpleDateFormat.format(currentDate));
    }

    private void handleButtonEvent() {
        buttonReturnBike.addActionListener(e -> {
            // TODO

        });
    }
}
