package com.hust.ebr.components.bike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.controller.AdminBikePageController;

import javax.swing.*;

public class AdminBikeListPane extends ADataListPane<Bike> {

    public AdminBikeListPane(ADataPageController<Bike> controller) {
        this.dataPageController = controller;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
        JButton editButton = new JButton("Edit");
        singlePane.addDataHandlingComponent(editButton);

        JButton deleteButton = new JButton("Delete");
        singlePane.addDataHandlingComponent(deleteButton);

        ((AdminBikePageController) dataPageController).onEdit(singlePane, editButton);
        ((AdminBikePageController) dataPageController).onDelete(singlePane, deleteButton);
    }
}
