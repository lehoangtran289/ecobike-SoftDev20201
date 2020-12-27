package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.*;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataCreateDialog;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.utils.DateUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BikeCreateDialog extends ADataCreateDialog<Bike> {

    private JTextField idField;
    private JTextField nameField;
    private JTextField weightField;
    private JTextField licensePlateField;
    private JTextField manufactureDateField;
    private JTextField producerField;
    private JTextField costField;
    private JComboBox<String> typesComboBox;
    private JComboBox<String> dockingStationIdComboBox;
    private JTextField batteryField;
    private JTextField loadCyclesField;
    private JTextField timeRemainingField;

    public BikeCreateDialog(IDataManageController<Bike> controller) {
        super(controller);
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
        idField = new JTextField(15);

        nameField = new JTextField(15);

        weightField = new JTextField(15);

        licensePlateField = new JTextField(15);

        manufactureDateField = new JTextField(15);

        producerField = new JTextField(15);

        costField = new JTextField(15);

        dockingStationIdComboBox = new JComboBox<>();
        List<String> stationIds = DockingStationApi.singleton().getStations(null)
                .stream()
                .map(DockingStation::getId)
                .collect(Collectors.toList());
        stationIds.forEach(id -> dockingStationIdComboBox.addItem(id));
//        dockingStationIdComboBox.addItem("none");
//        dockingStationIdComboBox.setSelectedItem(t.getDockingStationId() == null ? "none" : t
//        .getDockingStationId());
        dockingStationIdComboBox.setSelectedItem(stationIds.get(0));

        typesComboBox = new JComboBox<>();
        typesComboBox.addItem("NormalBike");
        typesComboBox.addItem("TwinBike");
        typesComboBox.addItem("EBike");
        typesComboBox.setSelectedItem("NormalBike");
        typesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (typesComboBox.getSelectedItem().toString()) {
                    case "EBike":
                        batteryField.setEditable(true);
                        loadCyclesField.setEditable(true);
                        timeRemainingField.setEditable(true);
                        break;
                    default:
                        batteryField.setEditable(false);
                        loadCyclesField.setEditable(false);
                        timeRemainingField.setEditable(false);
                        break;
                }
            }
        });

        batteryField = new JTextField();
        loadCyclesField = new JTextField();
        timeRemainingField = new JTextField();
        batteryField.setEditable(false);
        loadCyclesField.setEditable(false);
        timeRemainingField.setEditable(false);

        addNewField(idField, "Id ");
        addNewField(nameField, "Name ");
        addNewField(weightField, "Weight ");
        addNewField(licensePlateField, "License plate ");
        addNewField(manufactureDateField, "Manufacture date  ");
        addNewField(producerField, "Producer ");
        addNewField(costField, "Cost ");
        addNewField(dockingStationIdComboBox, "Docking station ID  ");
        addNewField(typesComboBox, "types");
        addNewField(batteryField, "battery");
        addNewField(loadCyclesField, "cycles");
        addNewField(timeRemainingField, "time remaining");
    }

    @Override
    public Bike getNewData() {
        Bike bike = new Bike();
        if (idField.getText() != null && !idField.getText().equals("")) {
            if (!BikeApi.singleton().getAllBikes().stream().map(Bike::getId).collect(Collectors.toList()).contains(idField.getText())) {
                bike.setId(idField.getText());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Bike id exists, please try again!",
                        "INVALID ID",
                        JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Bike id is empty, please try again!",
                    "INVALID ID",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (nameField.getText() != null && !nameField.getText().equals("")) {
            bike.setName(nameField.getText());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Bike name is empty, please try again!",
                    "INVALID NAME",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        try {
            System.out.println(Float.parseFloat(weightField.getText()));
            bike.setWeight(Float.parseFloat(weightField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Weight, please try again!",
                    "WRONG NUMBER FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (licensePlateField.getText() != null && !licensePlateField.getText().equals("")) {
            bike.setLicensePlate(licensePlateField.getText());
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
            bike.setManufacturingDate(DateUtils.newDateToSave(manufacturingDate));
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
            bike.setProducer(producerField.getText());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Producer is empty, please try again!",
                    "INVALID PRODUCER",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        try {
            bike.setCost(Long.parseLong(costField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Cost, please try again!",
                    "WRONG NUMBER FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (String.valueOf(dockingStationIdComboBox.getSelectedItem()).equals("none")) {
            bike.setDockingStationId(null);
        } else {
            bike.setDockingStationId(String.valueOf(dockingStationIdComboBox.getSelectedItem()));
        }

        if (String.valueOf(typesComboBox.getSelectedItem()).equals("EBike")) {
            EBike eBike = new EBike();
            eBike.setId(bike.getId());
            eBike.setName(bike.getName());
            eBike.setWeight(bike.getWeight());
            eBike.setLicensePlate(bike.getLicensePlate());
            eBike.setCost(bike.getCost());
            eBike.setManufacturingDate(bike.getManufacturingDate());
            eBike.setProducer(bike.getProducer());
            eBike.setStatus(Bike.Status.Available);
            eBike.setDockingStationId(bike.getDockingStationId());
            Motor motor = new Motor();
            motor.setId("motorX");
            motor.setBatteryPercentage(Integer.parseInt(batteryField.getText()));
            motor.setRemainingTime(Long.parseLong(timeRemainingField.getText()));
            motor.setLoadCycles(Integer.parseInt(loadCyclesField.getText()));
            eBike.setMotor(new Motor());
            return eBike;
        }

        if (String.valueOf(typesComboBox.getSelectedItem()).equals("NormalBike")) {
            NormalBike nBike = new NormalBike();
            nBike.setId(bike.getId());
            nBike.setName(bike.getName());
            nBike.setWeight(bike.getWeight());
            nBike.setLicensePlate(bike.getLicensePlate());
            nBike.setCost(bike.getCost());
            nBike.setManufacturingDate(bike.getManufacturingDate());
            nBike.setProducer(bike.getProducer());
            nBike.setStatus(Bike.Status.Available);
            nBike.setDockingStationId(bike.getDockingStationId());
            return nBike;
        }

        if (String.valueOf(typesComboBox.getSelectedItem()).equals("TwinBike")) {
            TwinBike tBike = new TwinBike();
            tBike.setId(bike.getId());
            tBike.setName(bike.getName());
            tBike.setWeight(bike.getWeight());
            tBike.setLicensePlate(bike.getLicensePlate());
            tBike.setCost(bike.getCost());
            tBike.setManufacturingDate(bike.getManufacturingDate());
            tBike.setProducer(bike.getProducer());
            tBike.setStatus(Bike.Status.Available);
            tBike.setDockingStationId(bike.getDockingStationId());
            return tBike;
        }

        return bike;
    }
}
