package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.components.dockingstationbikes.gui.EBRStationBikeList;
import com.hust.ebr.components.dockingstationbikes.controller.EBRUserStationBikeListController;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserStationListPane extends ADataListPane<DockingStation> {

    public UserStationListPane(ADataPageController<DockingStation> dataPageController) {
        this.dataPageController = dataPageController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {
        JButton viewDockButton = new JButton("View Details");
        singlePane.addDataHandlingComponent(viewDockButton);

        viewDockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EBRStationBikeList(new EBRUserStationBikeListController(), singlePane.getData());
            }
        });
    }
}
