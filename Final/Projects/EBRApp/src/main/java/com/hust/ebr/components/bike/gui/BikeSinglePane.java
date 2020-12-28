package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class BikeSinglePane extends ADataSinglePane<Bike> {

    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelWeight;
    private JLabel labelLicensePlate;
    private JLabel labelManufactureDate;
    private JLabel labelProducer;
    private JLabel labelCost;
    private JLabel labelStatus;
    private JLabel labelDockingStationId;

    public BikeSinglePane() {
        super();
    }

    public BikeSinglePane(Bike bike) {
        super(bike);
    }

    private void addNewField(JLabel label) {
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);
    }

    @Override
    public void buildControls() {
        super.buildControls();

        labelId = new JLabel();
        labelName = new JLabel();
        labelWeight = new JLabel();
        labelLicensePlate = new JLabel();
        labelManufactureDate = new JLabel();
        labelProducer = new JLabel();
        labelCost = new JLabel();
        labelStatus = new JLabel();
        labelDockingStationId = new JLabel();

        addNewField(labelId);
        addNewField(labelName);
        addNewField(labelWeight);
        addNewField(labelLicensePlate);
        addNewField(labelManufactureDate);
        addNewField(labelProducer);
        addNewField(labelCost);
        addNewField(labelStatus);
        addNewField(labelDockingStationId);
    }

    @Override
    public void displayData() {
        if (t != null) {
            labelId.setText("ID: " + t.getId());
            labelName.setText("Name: " + t.getName());
            labelWeight.setText("Weight: " + t.getWeight() + " kg");
            labelLicensePlate.setText("License plate: " + t.getLicensePlate());
            labelManufactureDate.setText("Manufacture date: " + new SimpleDateFormat("dd/MM/yyyy").format(t.getManufacturingDate()));
            labelProducer.setText("Producer: " + t.getProducer());
            labelCost.setText("Cost: " + t.getCost() + "VND");
            labelStatus.setText("Status: " + t.getStatus());
            labelDockingStationId.setText("Docking station ID: " + t.getDockingStationId());
        } else {
            labelId.setText(null);
            labelName.setText(null);
            labelWeight.setText(null);
            labelLicensePlate.setText(null);
            labelManufactureDate.setText(null);
            labelProducer.setText(null);
            labelCost.setText(null);
            labelStatus.setText(null);
            labelDockingStationId.setText(null);
        }
    }
}
