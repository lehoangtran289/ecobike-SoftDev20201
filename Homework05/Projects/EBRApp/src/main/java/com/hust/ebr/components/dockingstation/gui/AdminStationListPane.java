package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.controller.AdminStationPageController;
import com.hust.ebr.components.dockingstation.stationBikeList.controller.EBRAdminStationBikeListController;
import com.hust.ebr.components.dockingstation.stationBikeList.gui.EBRStationBikeList;

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
                    DockingStation newDockingStation =
                            ((AdminStationPageController) homePageController).updateDockingStation(dockingStation);
                    singlePane.updateData(newDockingStation);
                }
            }
        };

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DockingStationEditDialog(singlePane.getData(), controller);
            }
        });

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
