package com.hust.ebr.components.historyrental.gui;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public class RentalSinglePane extends ADataSinglePane<Rental> {

    private JLabel labelBikeId;
    private JLabel labelBikeType;
    private JLabel labelCardNumber;
    private JLabel labelCardOwner;
    private JLabel labelFromStationId;
    private JLabel labelToStationId;
    private JLabel labelRentalDate;
    private JLabel labelTotalTime;
    private JLabel labelTotalMoney;

    public RentalSinglePane() {
        super();
    }

    public RentalSinglePane(Rental rental) {
        super(rental);
    }

    private void addNewField(JLabel label) {
        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        add(label, c);
    }

    @Override
    public void buildControls() {
        super.buildControls();

        labelBikeId = new JLabel();
        labelBikeType = new JLabel();
        labelCardNumber = new JLabel();
        labelCardOwner = new JLabel();
        labelFromStationId = new JLabel();
        labelToStationId = new JLabel();
        labelRentalDate = new JLabel();
        labelTotalTime = new JLabel();
        labelTotalMoney = new JLabel();

        addNewField(labelBikeId);
        addNewField(labelBikeType);
        addNewField(labelCardNumber);
        addNewField(labelCardOwner);
        addNewField(labelFromStationId);
        addNewField(labelToStationId);
        addNewField(labelRentalDate);
        addNewField(labelTotalTime);
        addNewField(labelTotalMoney);
    }

    @Override
    public void displayData() {
        if (t != null) {
            labelBikeId.setText("Bike ID: " + t.getBikeId());
            labelBikeType.setText("Bike type: " + t.getBikeType());
            labelCardNumber.setText("CardNumber: " + t.getCardNumber());
            labelCardOwner.setText("CardOwner: " + t.getCardOwner());
            labelFromStationId.setText("From StationID: " + t.getFromStationId());
            labelToStationId.setText("To StationID: " + t.getToStationId());
            labelRentalDate.setText("Rental Date: " + t.getRentalDate());
            labelTotalTime.setText("Total time: " + t.getTotalTime());
            labelTotalMoney.setText("Total Money: " + t.getTotalMoney());
        }
    }
}
