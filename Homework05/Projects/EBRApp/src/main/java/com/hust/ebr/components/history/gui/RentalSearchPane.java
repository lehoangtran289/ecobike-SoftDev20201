package com.hust.ebr.components.history.gui;

import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RentalSearchPane extends ADataSearchPane {

    private JTextField idField;
    private JTextField cardNumberField;
    private JTextField cardOwnerField;
    private JTextField fromStationIdField;
    private JTextField toStationIdField;
    private JTextField rentalDateField;
    private JCheckBox normalBikeCheckBox, twinBikeCheckBox, eBikeCheckBox;

    public RentalSearchPane() {
        super();
    }

    @Override
    public void buildControls() {
        initItem();
        addNewField(idField, "Bike ID");
        addNewField(cardNumberField,"Card Number");
        addNewField(cardOwnerField,"Card Owner");
        addNewField(fromStationIdField,"From StationId");
        addNewField(toStationIdField,"To StationId");
        addNewField(rentalDateField, "Rental Date");
        addNewBox();
    }

    private void initItem() {
        idField = new JTextField(15);
        cardNumberField = new JTextField(15);
        cardOwnerField = new JTextField(15);
        fromStationIdField = new JTextField(15);
        toStationIdField = new JTextField(15);
        rentalDateField = new JTextField(15);
        normalBikeCheckBox = new JCheckBox("Normal Bike");
        twinBikeCheckBox = new JCheckBox("Twin Bike");
        eBikeCheckBox = new JCheckBox("EBike");
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

    private void addNewBox() {
        // Add checkbox bike type
        JLabel typesLabel = new JLabel("Types");
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(typesLabel, c);
        c.gridx = 1;
        c.gridy = row;
        add(normalBikeCheckBox, c);
        c.gridx = 2;
        c.gridy = row;
        add(twinBikeCheckBox, c);
        c.gridx = 3;
        c.gridy = row;
        add(eBikeCheckBox, c);
    }

    @Override
    public Map<String, String> getQueryParams() {
        Map<String, String> res = super.getQueryParams();

        if (!idField.getText().trim().equals("")) {
            res.put("bikeId", idField.getText().trim());
        }
        if (!cardNumberField.getText().trim().equals("")) {
            res.put("cardNumber", cardNumberField.getText().trim());
        }
        if (!cardOwnerField.getText().trim().equals("")) {
            res.put("cardOwner", cardOwnerField.getText().trim());
        }
        if (!fromStationIdField.getText().trim().equals("")) {
            res.put("fromStationId", fromStationIdField.getText().trim());
        }
        if (!toStationIdField.getText().trim().equals("")) {
            res.put("toStationId", toStationIdField.getText().trim());
        }
        if (!rentalDateField.getText().trim().equals("")) {
            res.put("rentalDate", rentalDateField.getText().trim());
        }

        List<String> bikeTypes = new ArrayList<>();
        String normalBike = "normalBike";
        String twinBike = "twinBike";
        String eBike = "eBike";

        if (normalBikeCheckBox.isSelected()) {
            bikeTypes.add(normalBike);
        }
        if (twinBikeCheckBox.isSelected()) {
            bikeTypes.add(twinBike);
        }
        if (eBikeCheckBox.isSelected()) {
            bikeTypes.add(eBike);
        }
        res.put("types", String.join(",", bikeTypes));

        return res;
    }
}
