package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataCreateDialog;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DockingStationCreateDialog extends ADataCreateDialog<DockingStation> {

    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField totalDockCountField;

    public DockingStationCreateDialog(IDataManageController<DockingStation> controller) {
        super(controller);
    }

    @Override
    public void buildControls() {
        idField = new JTextField(15);
        nameField = new JTextField(15);
        addressField = new JTextField(15);
        totalDockCountField = new JTextField(15);

        addNewField(idField, "id");
        addNewField(nameField, "Name");
        addNewField(addressField, "Address");
        addNewField(totalDockCountField, "Number of Docks");
    }

    @Override
    public DockingStation getNewData() {
        DockingStation station = new DockingStation();

        if (idField.getText() != null && !idField.getText().equals("")) {
            if (!DockingStationApi.singleton().getStations(null).stream().map(DockingStation::getId).collect(Collectors.toList()).contains(idField.getText())) {
                station.setId(idField.getText());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Station id exists, please try again!",
                        "INVALID ID",
                        JOptionPane.PLAIN_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Station id is empty, please try again!",
                    "INVALID ID",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (nameField.getText() != null && !nameField.getText().equals(""))
            station.setStationName(nameField.getText());
        else {
            JOptionPane.showMessageDialog(null,
                    "Station name is empty, please try again!",
                    "INVALID NAME",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        if (addressField.getText() != null && !addressField.getText().equals(""))
            station.setStationName(addressField.getText());
        else {
            JOptionPane.showMessageDialog(null,
                    "Station address is empty, please try again!",
                    "INVALID ADDRESS",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        try {
            station.setTotalDockCount(Integer.parseInt(totalDockCountField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number of docks, please try again!",
                    "WRONG NUMBER FORMAT",
                    JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        station.setEmptyDockCount(station.getTotalDockCount());
        station.setNormalBikeCount(0);
        station.setTwinBikeCount(0);
        station.setEBikeCount(0);
        station.setBikeIds(new ArrayList<String>());

        return station;
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


}
