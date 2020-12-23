package com.hust.ebr.components.dockingstation.stationBikeList.gui;

import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StationBikeListSearchPane extends ADataSearchPane {
    private JTextField nameField;
    private JTextField licenseField;
    private JTextField manufacturerField;
    private JTextField bikeIdField;
    private JCheckBox singleBikeBox;
    private JCheckBox twinBikeBox;
    private JCheckBox eBikeBox;

    public StationBikeListSearchPane() {
        super();
    }

    @Override
    public void buildControls() {
        nameField = new JTextField(15);
        licenseField = new JTextField(15);
        manufacturerField = new JTextField(15);
        bikeIdField = new JTextField(15);
        addNewField(nameField, "Name");
        addNewField(licenseField, "License");
        addNewField(manufacturerField, "Manufacturer");
        addNewField(bikeIdField, "Bike Id");

        int row = getLastRowIndex();
        singleBikeBox = new JCheckBox("Single Bike");
        twinBikeBox = new JCheckBox("Twin Bike");
        eBikeBox = new JCheckBox("Electrical Bike");
        c.gridx = 0;
        c.gridy = row;
        JLabel typeLabel = new JLabel("Type");
        add(typeLabel, c);
        c.gridx = 1;
        add(singleBikeBox, c);
        c.gridx = 2;
        add(twinBikeBox, c);
        c.gridx = 3;
        add(eBikeBox, c);
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
        List<String> types = new ArrayList<String>();

        if (singleBikeBox.isSelected())
            types.add("normalBike");
        if (twinBikeBox.isSelected())
            types.add("twinBike");
        if (eBikeBox.isSelected())
            types.add("eBike");

        String typeQueryParam = String.join(",", types);
        res.put("types", typeQueryParam);

        if (!nameField.getText().trim().equals("")) {
            res.put("name", nameField.getText().trim());
        }
        if (!manufacturerField.getText().trim().equals("")) {
            res.put("manufacturer", manufacturerField.getText().trim());
        }
        if (!licenseField.getText().trim().equals("")) {
            res.put("license", licenseField.getText().trim());
        }
        if (!bikeIdField.getText().trim().equals("")) {
            res.put("id", bikeIdField.getText().trim());
        }
        System.out.println(res);
        return res;
    }
}
