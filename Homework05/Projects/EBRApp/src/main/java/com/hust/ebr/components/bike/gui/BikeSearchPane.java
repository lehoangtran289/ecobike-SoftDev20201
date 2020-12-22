package com.hust.ebr.components.bike.gui;

import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;

import javax.swing.*;
import java.util.Map;

public class BikeSearchPane extends ADataSearchPane {

    private JTextField idField;
    private JTextField nameField;
    private JTextField licensePlateField;
    private JTextField producerField;
    private JTextField dockingStationIdField;
    private JComboBox<String> statusComboBox;
    private JCheckBox normalBikeCheckBox, twinBikeCheckBox, eBikeCheckBox;

    public BikeSearchPane() {
        super();
    }

    @Override
    public void buildControls() {
        initItem();
        addNewField(idField, "ID");
        addNewField(nameField,"Name");
        addNewField(licensePlateField,"License plate");
        addNewField(producerField,"Producer");
        addNewField(dockingStationIdField,"Docking station ID");
        addNewBox();
    }

    private void initItem() {
        idField = new JTextField(15);
        nameField = new JTextField(15);
        licensePlateField = new JTextField(15);
        producerField = new JTextField(15);
        dockingStationIdField = new JTextField(15);
        statusComboBox = new JComboBox<>();
        normalBikeCheckBox = new JCheckBox("Normal Bike");
        twinBikeCheckBox = new JCheckBox("Twin Bike");
        eBikeCheckBox = new JCheckBox("EBike");

        // Init combo box for choosing bike's current status
        String available = "Available";
        String renting = "Renting";
        String both = "Both";
        statusComboBox.addItem(available);
        statusComboBox.addItem(renting);
        statusComboBox.addItem(both);
        statusComboBox.setSelectedItem(both);
        statusComboBox.setBounds(90, 85, 245, 31);
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
        // Add combobox status
        JLabel nameLabel = new JLabel("Status");
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(nameLabel, c);
        c.gridx = 1;
        c.gridy = row;
        add(statusComboBox, c);

        // Add checkbox bike type
        JLabel typesLabel = new JLabel("Types");
        row = getLastRowIndex();
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
            res.put("id", idField.getText().trim());
        }
        if (!nameField.getText().trim().equals("")) {
            res.put("name", nameField.getText().trim());
        }
        if (!licensePlateField.getText().trim().equals("")) {
            res.put("licensePlate", licensePlateField.getText().trim());
        }
        if (!producerField.getText().trim().equals("")) {
            res.put("producer", producerField.getText().trim());
        }
        if (!dockingStationIdField.getText().trim().equals("")) {
            res.put("dockingStationId", dockingStationIdField.getText().trim());
        }
        if (!String.valueOf(statusComboBox.getSelectedItem()).equals("Both")) {
            res.put("status", String.valueOf(statusComboBox.getSelectedItem()));
        }

        String bikeType = "";
        String normalBike = "normalBike";
        String twinBike = "twinBike";
        String eBike = "eBike";

        if (normalBikeCheckBox.isSelected()) {
            bikeType += normalBike + ",";
        }
        if (twinBikeCheckBox.isSelected()) {
            bikeType += twinBike + ",";
        }
        if (eBikeCheckBox.isSelected()) {
            bikeType += eBike + ",";
        }

        if (!bikeType.isEmpty()) {
            bikeType = bikeType.replaceAll(",$", "");
            res.put("type", bikeType);
        }

        return res;
    }
}
