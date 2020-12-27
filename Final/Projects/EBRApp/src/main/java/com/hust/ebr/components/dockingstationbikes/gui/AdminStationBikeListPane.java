package com.hust.ebr.components.dockingstationbikes.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

public class AdminStationBikeListPane extends ADataListPane<Bike> {
    public AdminStationBikeListPane(ADataPageController<Bike> bikeListController) {
        this.dataPageController = bikeListController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {

    }
}
