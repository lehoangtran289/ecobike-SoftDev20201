package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.controller.IDataUpdateController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.controller.AdminStationPageController;
import com.hust.ebr.components.dockingstationbikes.controller.EBRAdminStationBikeListController;
import com.hust.ebr.components.dockingstationbikes.gui.EBRStationBikeList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminStationListPane extends ADataListPane<DockingStation> {

    public AdminStationListPane(ADataPageController<DockingStation> homePageController) {
        this.homePageController = homePageController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {
        JButton button = new JButton("Edit");
        singlePane.addDataHandlingComponent(button);

        ((AdminStationPageController) homePageController).onEdit(singlePane, button);

        JButton viewDockButton = new JButton("View Details");
        singlePane.addDataHandlingComponent(viewDockButton);

        viewDockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EBRStationBikeList(new EBRAdminStationBikeListController(), singlePane.getData());
            }
        });
    }
}
