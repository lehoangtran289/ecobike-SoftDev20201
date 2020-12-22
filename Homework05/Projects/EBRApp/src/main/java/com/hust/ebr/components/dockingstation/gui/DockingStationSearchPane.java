package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;

import javax.swing.*;
import java.util.Map;

public class DockingStationSearchPane extends ADataSearchPane {

    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;

    public DockingStationSearchPane() {
        super();
    }

    @Override
    public void buildControls() {
        addNewField(idField, "ID");
        addNewField(nameField, "Name");
        addNewField(addressField, "Address");
    }

    private void addNewField(JTextField field, String title) {
        JLabel nameLabel = new JLabel(title);
        field = new JTextField(15);
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(nameLabel, c);
        c.gridx = 1;
        c.gridy = row;
        add(field, c);
    }

    @Override
    public Map<String, String> getQueryParams() {
        Map<String, String> res = super.getQueryParams();

        if (!idField.getText().trim().equals("")) {
            res.put("id", idField.getText().trim());
        }
        if (!nameField.getText().trim().equals("")) {
            res.put("name", nameField.getText().trim());
        }
        if (!addressField.getText().trim().equals("")) {
            res.put("address", addressField.getText().trim());
        }

        return res;
    }
}
