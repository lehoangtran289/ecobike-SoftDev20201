package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataEditDialog;

import javax.swing.*;

public class DockingStationEditDialog extends ADataEditDialog<DockingStation> {

    private JTextField nameField;
    private JTextField addressField;

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

        addNewField(nameField, "Station name");
        addNewField(addressField, "Station address");
    }

    @Override
    public DockingStation getNewData() {
        t.setStationName(nameField.getText());
        t.setStationAddress(addressField.getText());

        return t;
    }
}
