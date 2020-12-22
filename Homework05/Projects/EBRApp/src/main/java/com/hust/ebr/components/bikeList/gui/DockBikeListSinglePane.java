package com.hust.ebr.components.bikeList.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public class DockBikeListSinglePane extends ADataSinglePane<Bike> {
    private JLabel labelCode;
    private JLabel labelName;
    private JLabel labelWeight;
    private JLabel labelLicense;
    private JLabel labelManufacturerDate;
    private JLabel labelManufacturer;
    private JLabel labelCost;

    private void addNewField(JLabel label) {
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);
    }

    @Override
    public void buildControls() {
        super.buildControls();
        labelCode = new JLabel();
        labelName = new JLabel();
        labelWeight = new JLabel();
        labelLicense = new JLabel();
        labelManufacturerDate = new JLabel();
        labelManufacturer = new JLabel();
        labelCost = new JLabel();

        addNewField(labelCode);
        addNewField(labelName);
        addNewField(labelWeight);
        addNewField(labelLicense);
        addNewField(labelManufacturerDate);
        addNewField(labelManufacturer);
        addNewField(labelCost);
    }

    @Override
    public void displayData() {
        if (t != null) {
            labelCode.setText("Bike Code: " + t.getId());
            labelName.setText("Name: " + t.getName());
            labelWeight.setText("Weight" + t.getWeight());
            labelLicense.setText("License Plate: " + t.getLicensePlate());
            labelManufacturerDate.setText("Manufacturer Date: " + t.getManufacturingDate());
            labelManufacturer.setText("Manufacturer: " + t.getProducer());
            labelCost.setText("Cost: " + t.getCost());
        }
    }
}
