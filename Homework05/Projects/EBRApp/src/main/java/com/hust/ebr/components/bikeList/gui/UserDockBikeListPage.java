package com.hust.ebr.components.bikeList.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataDockBikeListController;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDockBikeListPage extends ADataListPane<Bike> {

    public UserDockBikeListPage(ADataDockBikeListController<Bike> bikeListController) {
        this. = bikeListController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
        JButton viewBikeButton = new JButton("View Details");
        singlePane.addDataHandlingComponent(viewBikeButton);

        viewBikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
