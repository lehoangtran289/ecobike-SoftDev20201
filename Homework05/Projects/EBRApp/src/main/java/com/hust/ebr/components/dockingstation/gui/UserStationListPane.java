package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.app.bikeList.ERBStationBikeList;
import com.hust.ebr.app.bikeList.ERBStationBikeListController;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserStationListPane extends ADataListPane<DockingStation> {

    public UserStationListPane(ADataHomePageController<DockingStation> homePageController) {
        this.homePageController = homePageController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {
        JButton viewDockButton = new JButton("View Details");
        singlePane.addDataHandlingComponent(viewDockButton);

        viewDockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ERBStationBikeList(new ERBStationBikeListController(), singlePane.getData());
            }
        });
    }
}
