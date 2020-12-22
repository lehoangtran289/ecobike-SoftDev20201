package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataEditDialog;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BikeEditDialog extends ADataEditDialog<Bike> {

    private JTextField nameField;
    private JTextField weightField;
    private JTextField licensePlateField;
    private JTextField manufactureDateField;
    private JTextField producerField;
    private JTextField costField;
    private JTextField dockingStationIdField;

    public BikeEditDialog(Bike bike, IDataManageController<Bike> controller) {
        super(bike, controller);
    }

    protected void addNewField(JTextField textField, String label) {
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
        nameField.setText(t.getName());

        weightField = new JTextField(15);
        weightField.setText(String.valueOf(t.getWeight()));

        licensePlateField = new JTextField(15);
        licensePlateField.setText(t.getLicensePlate());

        manufactureDateField = new JTextField(15);
        manufactureDateField.setText(String.valueOf(t.getManufacturingDate()));

        producerField = new JTextField(15);
        producerField.setText(t.getProducer());

        costField = new JTextField(15);
        costField.setText(String.valueOf(t.getCost()));

        dockingStationIdField = new JTextField(15);
        dockingStationIdField.setText(t.getDockingStationId());

        addNewField(nameField, "Name");
        addNewField(weightField, "Weight");
        addNewField(licensePlateField, "License plate");
        addNewField(manufactureDateField, "Manufacture date");
        addNewField(producerField, "Producer");
        addNewField(costField, "Cost");
        addNewField(dockingStationIdField, "Docking station ID");
    }

    @Override
    public Bike getNewData() {
        t.setName(nameField.getText());
        t.setWeight(Float.parseFloat(weightField.getText()));
        t.setLicensePlate(licensePlateField.getText());
        try {
            t.setManufacturingDate(new SimpleDateFormat("dd/MM/yyyy").parse(manufactureDateField.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.setProducer(producerField.getText());
        t.setCost(Float.parseFloat(costField.getText()));
        t.setDockingStationId(dockingStationIdField.getText());
        return t;
    }
}
