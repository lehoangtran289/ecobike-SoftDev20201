package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public class AdminDockingStationListPane extends ADataListPane<DockingStation> {

    public AdminDockingStationListPane(ADataHomePageController<DockingStation> homePageController) {
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

            }
        };

        button.addActionListener(e -> new DockingStationEditDialog(singlePane.getData(), controller));
    }
}
