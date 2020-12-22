package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public class DockingStationSinglePane extends ADataSinglePane<DockingStation> {

    private JLabel labelID;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelEmptyDock;
    private JLabel labelNormalBike;
    private JLabel labelTwinBike;
    private JLabel labelEBike;

    public DockingStationSinglePane() {
        super();
    }

    public DockingStationSinglePane(DockingStation dockingStation) {
        super(dockingStation);
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

        labelID = new JLabel();
        labelName = new JLabel();
        labelAddress = new JLabel();
        labelEmptyDock = new JLabel();
        labelNormalBike = new JLabel();
        labelTwinBike = new JLabel();
        labelEBike = new JLabel();

        addNewField(labelID);
        addNewField(labelName);
        addNewField(labelAddress);
        addNewField(labelEmptyDock);
        addNewField(labelNormalBike);
        addNewField(labelTwinBike);
        addNewField(labelEBike);

    }

    @Override
    public void displayData() {
        if (t != null) {
            labelID.setText("ID: " + t.getId());
            labelName.setText("Name: " + t.getStationName());
            labelAddress.setText("Address: " + t.getStationAddress());
            labelEmptyDock.setText("Empty docks: " + t.getEmptyDockCount());
            labelNormalBike.setText("Normal bikes: " + t.getNormalBikeCount());
            labelTwinBike.setText("Twin bikes: " + t.getTwinBikeCount());
            labelEBike.setText("EBikes: " + t.getEBikeCount());
        }

    }
}
