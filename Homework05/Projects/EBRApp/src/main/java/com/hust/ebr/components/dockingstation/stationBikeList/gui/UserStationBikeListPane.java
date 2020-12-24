package com.hust.ebr.components.dockingstation.stationBikeList.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

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

            }
        });
    }
}
