package com.hust.ebr.components.returnbike.gui;

import com.hust.ebr.components.returnbike.controller.EBRUserReturnBikeController;

import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class EBRUserReturnBike extends JFrame {
    private JDialog rootDialog;
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
    private JLabel labelDepositCost;
    private JLabel labelStatus;
    private JLabel labelFromDocking;
    private JLabel labelTotalCost;
    private JLabel labelRentStartTime;
    private JLabel labelRentReturnTime;
    private JButton PAYANDRETURNButton;
    private JComboBox<String> stationIdSelectionBox;
    private JLabel labelToDocking;

    public EBRUserReturnBike(EBRUserReturnBikeController controller) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma z");
        if (controller.getBike() != null) {
            labelId.setText("ID: " + controller.getBike().getId());
            labelName.setText("Name: " + controller.getBike().getName());
            labelWeight.setText("Weight: " + controller.getBike().getWeight());
            labelLicensePlate.setText("License plate: " + controller.getBike().getLicensePlate());
            labelCurrentDate.setText("Current Date: " + customFormatter.format(ZonedDateTime.now()));
            labelManufactureDate.setText("Manufacture date: " + controller.getBike().getManufacturingDate());
            labelProducer.setText("Producer: " + controller.getBike().getProducer());
            labelDepositCost.setText("Deposit Cost: " + controller.getBike().getCost());
            labelTotalCost.setText("Total Cost: " + controller.getTotalCost());
            labelStatus.setText("Status: " + controller.getBike().getStatus());
            labelFromDocking.setText("From docking: " + controller.getBike().getDockingStationId());
            labelToDocking.setText("To Docking: " + controller.getCurrentStationId());
        }
        if (controller.getCreditCard() != null) {
            labelCardOwner.setText("Card owner: " + controller.getCreditCard().getCardOwner());
            labelCardNumber.setText("Card number: " + controller.getCreditCard().getCardNumber());
        }
        labelRentReturnTime.setText("Return Time: " + customFormatter.format(controller.getTimeReturn()));
        labelRentStartTime.setText("Rent Time: " + customFormatter.format(controller.getTimeBegin()));

        rootDialog = new JDialog();
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(21, 1));
        rootPanel.add(new JLabel("USER DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelCardOwner);
        rootPanel.add(labelCardNumber);
        rootPanel.add(labelCurrentDate);
        rootPanel.add(new JLabel("BIKE DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelId);
        rootPanel.add(labelName);
        rootPanel.add(labelWeight);
        rootPanel.add(labelLicensePlate);
        rootPanel.add(labelManufactureDate);
        rootPanel.add(labelStatus);
        rootPanel.add(labelProducer);
        rootPanel.add(labelFromDocking);
        rootPanel.add(labelToDocking);
        rootPanel.add(stationIdSelectionBox);
        stationIdSelectionBox.addItem("ds1");
        stationIdSelectionBox.addItem("ds2");
        stationIdSelectionBox.addItem("ds3");
        stationIdSelectionBox.setBounds(90, 85, 245, 31);
        controller.handleButtonEvent(rootDialog, stationIdSelectionBox, PAYANDRETURNButton, labelToDocking);
        rootPanel.add(new JLabel("RENTAL DETAILS", SwingConstants.CENTER));
        rootPanel.add(labelRentStartTime);
        rootPanel.add(labelRentReturnTime);
        rootPanel.add(labelDepositCost);
        rootPanel.add(labelTotalCost);
        rootPanel.add(PAYANDRETURNButton);
        rootDialog.setTitle("Rental Detail");
        rootDialog.setContentPane(rootPanel);
        rootDialog.setSize(600, 500);
        rootDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        rootDialog.setLocationRelativeTo(null);
        rootDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        rootDialog.setVisible(true);

    }
}