package com.hust.ebr.components.bikeList.gui;

import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;

import javax.swing.*;
import java.util.Map;

public class DockBikeListSearchPane extends ADataSearchPane {
    private JTextField nameField;
    private JTextField licenseField;
    private JTextField manufacturerField;
    private JTextField codeField;

    @Override
    public void buildControls() {
        nameField = new JTextField(15);
        licenseField = new JTextField(15);
        manufacturerField = new JTextField(15);
        codeField = new JTextField(15);
        addNewField(nameField, "Name");
        addNewField(licenseField, "License");
        addNewField(manufacturerField, "Manufacturer");
        addNewField(codeField, "Bike Code");
    }

    private void addNewField(JTextField field, String title) {
        JLabel nameLabel = new JLabel(title);
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

        if (!nameField.getText().trim().equals("")) {
            res.put("name", nameField.getText().trim());
        }
        if (!manufacturerField.getText().trim().equals("")) {
            res.put("manufacturer", manufacturerField.getText().trim());
        }
        if (!licenseField.getText().trim().equals("")) {
            res.put("license", licenseField.getText().trim());
        }
        if (!codeField.getText().trim().equals("")) {
            res.put("bikeCode", codeField.getText().trim());
        }

        return res;
    }
}
