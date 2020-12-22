package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.controller.IDataManageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.controller.AdminBikePageController;

import javax.swing.*;

public class AdminBikeListPane extends ADataListPane<Bike> {

    public AdminBikeListPane(ADataHomePageController<Bike> controller) {
        this.homePageController = controller;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
        JButton button = new JButton("Edit");
        singlePane.addDataHandlingComponent(button);

        IDataManageController<Bike> manageController = new IDataManageController<Bike>() {
            @Override
            public void create(Bike bike) {

            }

            @Override
            public void read(Bike bike) {

            }

            @Override
            public void delete(Bike bike) {

            }

            @Override
            public void update(Bike bike) {
                if (homePageController instanceof AdminBikePageController) {
                    Bike newBike = ((AdminBikePageController) homePageController).updateBike(bike);
                    singlePane.updateData(newBike);
                }
            }
        };

        button.addActionListener(e -> {
            new BikeEditDialog(singlePane.getData(), manageController);
        });
    }
}
