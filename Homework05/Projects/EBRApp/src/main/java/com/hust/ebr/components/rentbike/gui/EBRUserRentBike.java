package com.hust.ebr.components.rentbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRUserRentBike extends JFrame{

    private JPanel rootPanel;
    private JLabel labelCardOwner;
    private JLabel labelCardNumber;
    private JLabel labelCurrentDate;
    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelWeight;
    private JLabel labelLicensePlate;
    private JLabel labelManufactureDate;
    private JLabel labelProducer;
    private JLabel labelCost;
    private JLabel labelStatus;
    private JLabel labelFromDocking;
    private JButton buttonReturnBike;

    private CreditCard creditCard;
    private Bike bike;
    private long timeBegin;

    public EBRUserRentBike(EBRUserRentBikeController controller, Bike bike, CreditCard creditCard) {
        this.bike = bike;
        this.creditCard = creditCard;
        timeBegin = System.currentTimeMillis();

        setTitle("Rent Bike Detail");
        setContentPane(rootPanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        displayData();
        handleButtonEvent();
    }

    public void displayData() {
        if (bike != null) {
            labelId.setText("ID: " + bike.getId());
            labelName.setText("Name: " + bike.getName());
            labelWeight.setText("Weight: " + bike.getWeight());
            labelLicensePlate.setText("License plate: " + bike.getLicensePlate());
            labelManufactureDate.setText("Manufacture date: " + bike.getManufacturingDate());
            labelProducer.setText("Producer: " + bike.getProducer());
            labelCost.setText("Cost: " + bike.getCost());
            labelStatus.setText("Status: " + bike.getStatus());
            labelFromDocking.setText("From docking: " + bike.getDockingStationId());
        }

        if (creditCard != null) {
            labelCardOwner.setText("Card owner: " + creditCard.getCardOwner());
            labelCardNumber.setText("Card number: " + creditCard.getCardNumber());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        labelCurrentDate.setText(simpleDateFormat.format(currentDate));
    }

    private void handleButtonEvent() {
        buttonReturnBike.addActionListener(e -> {
            // TODO

        });
    }
}
