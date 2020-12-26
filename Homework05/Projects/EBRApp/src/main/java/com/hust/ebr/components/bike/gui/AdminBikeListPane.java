package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.controller.IDataUpdateController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.controller.AdminBikePageController;

import javax.swing.*;

public class AdminBikeListPane extends ADataListPane<Bike> {

    public AdminBikeListPane(ADataPageController<Bike> controller) {
        this.homePageController = controller;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
        JButton button = new JButton("Edit");
        singlePane.addDataHandlingComponent(button);

        ((AdminBikePageController) homePageController).onEdit(singlePane, button);
    }
}
