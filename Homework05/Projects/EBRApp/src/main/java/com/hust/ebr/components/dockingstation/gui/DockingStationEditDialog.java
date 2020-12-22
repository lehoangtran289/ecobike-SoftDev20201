package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataEditDialog;

import javax.swing.*;

public class DockingStationEditDialog extends ADataEditDialog<DockingStation> {

    private JTextField nameField;
    private JTextField addressField;
    private JTextField emptyDockField;
    private JTextField normalBikeField;
    private JTextField twinBikeField;
    private JTextField eBikeField;

    public DockingStationEditDialog(DockingStation dockingStation, IDataManageController<DockingStation> controller) {
        super(dockingStation, controller);
    }

    private void addNewField(JTextField textField, String label) {
        int row = getLastRowIndex();
        JLabel titleLabel = new JLabel(label);
        c.gridx = 0;
        c.gridy = row;
        getContentPane().add(titleLabel, c);
        c.gridx = 1;
        c.gridy = row;
        getContentPane().add(textField, c);
    }

    @Override
    public void buildControls() {
        nameField = new JTextField(15);
        nameField.setText(t.getStationName());

        addressField = new JTextField(15);
        addressField.setText(t.getStationAddress());

        emptyDockField = new JTextField(15);
        emptyDockField.setText(String.valueOf(t.getEmptyDockCount()));

        normalBikeField = new JTextField(15);
        normalBikeField.setText(String.valueOf(t.getNormalBikeCount()));

        twinBikeField = new JTextField(15);
        twinBikeField.setText(String.valueOf(t.getTwinBikeCount()));

        eBikeField = new JTextField(15);
        eBikeField.setText(String.valueOf(t.getEBikeCount()));

        addNewField(nameField, "Station name");
        addNewField(addressField, "Station address");
        addNewField(emptyDockField, "Empty docks");
        addNewField(normalBikeField, "Normal bikes");
        addNewField(twinBikeField, "Twin bikes");
        addNewField(eBikeField, "EBikes");
    }

    @Override
    public DockingStation getNewData() {
        t.setStationName(nameField.getText());
        t.setStationAddress(addressField.getText());
        t.setEmptyDockCount(Integer.parseInt(emptyDockField.getText()));
        t.setNormalBikeCount(Integer.parseInt(normalBikeField.getText()));
        t.setTwinBikeCount(Integer.parseInt(twinBikeField.getText()));
        t.setEBikeCount(Integer.parseInt(eBikeField.getText()));

        return t;
    }
}
