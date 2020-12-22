package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.controller.AdminStationPageController;

import javax.swing.*;

public class AdminStationListPane extends ADataListPane<DockingStation> {

    public AdminStationListPane(ADataHomePageController<DockingStation> homePageController) {
        this.homePageController = homePageController;
    }
    @Override
    public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {
        JButton button = new JButton("Edit");
        singlePane.addDataHandlingComponent(button);

        IDataManageController<DockingStation> controller = new IDataManageController<DockingStation>() {
            @Override
            public void create(DockingStation dockingStation) {

            }

            @Override
            public void read(DockingStation dockingStation) {

            }

            @Override
            public void delete(DockingStation dockingStation) {

            }

            @Override
            public void update(DockingStation dockingStation) {
                if (homePageController instanceof AdminStationPageController) {
                    DockingStation newDockingStation = ((AdminStationPageController) homePageController).updateDockingStation(dockingStation);
                    singlePane.updateData(newDockingStation);
                }
            }
        };

        button.addActionListener(e -> new DockingStationEditDialog(singlePane.getData(), controller));
    }
}
