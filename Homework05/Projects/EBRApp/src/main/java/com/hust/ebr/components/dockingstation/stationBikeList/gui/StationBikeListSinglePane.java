package com.hust.ebr.components.dockingstation.stationBikeList.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public class StationBikeListSinglePane extends ADataSinglePane<Bike> {
    private JLabel labelBikeId;
    private JLabel labelBikeName;
    private JLabel labelWeight;
    private JLabel labelLicensePlate;
    private JLabel labelManufacturerDate;
    private JLabel labelProducer;
    private JLabel labelCost;
    private JLabel labelStatus;
    private JLabel labelStationId;

    private void addNewField(JLabel label) {
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);
    }

    @Override
    public void buildControls() {
        super.buildControls();
        labelBikeId = new JLabel();
        labelBikeName = new JLabel();
        labelWeight = new JLabel();
        labelLicensePlate = new JLabel();
        labelManufacturerDate = new JLabel();
        labelProducer = new JLabel();
        labelCost = new JLabel();
        labelStatus = new JLabel();
        labelStationId = new JLabel();

        addNewField(labelBikeId);
        addNewField(labelBikeName);
        addNewField(labelWeight);
        addNewField(labelLicensePlate);
        addNewField(labelManufacturerDate);
        addNewField(labelProducer);
        addNewField(labelCost);
        addNewField(labelStatus);
        addNewField(labelStationId);
    }

    @Override
    public void displayData() {
        if (t != null) {
            labelBikeId.setText("Bike Id: " + t.getId());
            labelBikeName.setText("Name: " + t.getName());
            labelWeight.setText("Weight: " + t.getWeight());
            labelLicensePlate.setText("License Plate: " + t.getLicensePlate());
            labelManufacturerDate.setText("Manufacturer Date: " + t.getManufacturingDate());
            labelProducer.setText("Producer: " + t.getProducer());
            labelCost.setText("Cost: " + t.getCost());
            labelStatus.setText("Status: " + t.getStatus());
            labelStationId.setText("StationId: " + t.getDockingStationId());
        }
    }
}
