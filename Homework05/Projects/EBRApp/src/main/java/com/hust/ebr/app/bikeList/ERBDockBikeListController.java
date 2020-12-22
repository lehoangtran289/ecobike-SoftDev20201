package com.hust.ebr.app.bikeList;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataDockBikeListController;
import com.hust.ebr.components.bikeList.controller.UserDockBikeListPageController;

import javax.swing.*;

public class ERBDockBikeListController {
    public ERBDockBikeListController() {

    }

    public JPanel getBikeListPage() {
        ADataDockBikeListController<Bike> controller = new UserDockBikeListPageController();
        return controller.getBikeListPane();
    }
}
