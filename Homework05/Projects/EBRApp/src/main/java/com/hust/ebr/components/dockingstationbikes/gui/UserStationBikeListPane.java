package com.hust.ebr.components.dockingstationbikes.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.rentbike.gui.RentBikeCreditCardDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserStationBikeListPane extends ADataListPane<Bike> {

    public UserStationBikeListPane(ADataPageController<Bike> bikeListController) {
        this.homePageController = bikeListController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
        JButton rentButton = new JButton("Rent");
        singlePane.addDataHandlingComponent(rentButton);

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bike bike = singlePane.getData();
                if (bike.getStatus() == Bike.Status.Available) {
//                    homePageController.
                    new RentBikeCreditCardDialog(bike);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Someone is renting this bike!",
                            "BIKE NOT AVAILABLE",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
