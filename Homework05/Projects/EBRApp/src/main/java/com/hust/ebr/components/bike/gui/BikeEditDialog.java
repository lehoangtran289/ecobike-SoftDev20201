package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataEditDialog;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.utils.DateUtils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BikeEditDialog extends ADataEditDialog<Bike> {

    private JTextField nameField;
    private JTextField weightField;
    private JTextField licensePlateField;
    private JTextField manufactureDateField;
    private JTextField producerField;
    private JTextField costField;
    private JComboBox<String> dockingStationIdComboBox;

    public BikeEditDialog(Bike bike, IDataManageController<Bike> controller) {
        super(bike, controller);
    }

    protected void addNewField(JComponent textField, String label) {
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
        manufactureDateField.setText(new SimpleDateFormat("dd/MM/yyyy").format(t.getManufacturingDate()));

        producerField = new JTextField(15);
        producerField.setText(t.getProducer());

        costField = new JTextField(15);
        costField.setText(String.valueOf(t.getCost()));

        dockingStationIdComboBox = new JComboBox<>();
        List<String> stationIds = new DockingStationApi().getStations(null)
                .stream()
                .map(DockingStation::getId)
                .collect(Collectors.toList());
        stationIds.forEach(id -> dockingStationIdComboBox.addItem(id));
//        dockingStationIdComboBox.addItem("none");
//        dockingStationIdComboBox.setSelectedItem(t.getDockingStationId() == null ? "none" : t.getDockingStationId());
        dockingStationIdComboBox.setSelectedItem(t.getDockingStationId());

        addNewField(nameField, "Name ");
        addNewField(weightField, "Weight ");
        addNewField(licensePlateField, "License plate ");
        addNewField(manufactureDateField, "Manufacture date  ");
        addNewField(producerField, "Producer ");
        addNewField(costField, "Cost ");
        addNewField(dockingStationIdComboBox, "Docking station ID  ");
    }

    @Override
    public Bike getNewData() {
        if (nameField.getText() != null && !nameField.getText().equals("")) {
            t.setName(nameField.getText());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Bike name is empty, please try again!",
                    "INVALID NAME",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        try {
            t.setWeight(Float.parseFloat(weightField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Weight, please try again!",
                    "WRONG NUMBER FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (licensePlateField.getText() != null && !licensePlateField.getText().equals("")) {
            t.setLicensePlate(licensePlateField.getText());
        } else {
            JOptionPane.showMessageDialog(null,
                    "License plate is empty, please try again!",
                    "INVALID LICENSE PLATE",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        //check valid date && date <= now
        try {
            Date manufacturingDate = new SimpleDateFormat("dd/MM/yyyy").parse(manufactureDateField.getText());
            if (!manufactureDateField.getText().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
                throw new NumberFormatException();
            if (manufacturingDate.after(new Date()))
                throw new DateTimeException("invalid date");
            t.setManufacturingDate(DateUtils.newDateToSave(manufacturingDate));
        } catch (ParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Date, please try again!",
                    "WRONG DATE FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(null,
                    "Date is in future, please try again!",
                    "INVALID DATE",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (producerField.getText() != null && !producerField.getText().equals("")) {
            t.setProducer(producerField.getText());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Producer is empty, please try again!",
                    "INVALID PRODUCER",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        try {
            t.setCost(Float.parseFloat(costField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Cost, please try again!",
                    "WRONG NUMBER FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (String.valueOf(dockingStationIdComboBox.getSelectedItem()).equals("none")) {
            t.setDockingStationId(null);
        } else {
            t.setDockingStationId(String.valueOf(dockingStationIdComboBox.getSelectedItem()));
        }

        return t;
    }
}
