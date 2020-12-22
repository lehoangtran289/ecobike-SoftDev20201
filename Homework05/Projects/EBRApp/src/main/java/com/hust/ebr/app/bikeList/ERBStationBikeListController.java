package com.hust.ebr.app.bikeList;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.bikeList.controller.UserStationBikeListPageController;

import javax.swing.*;

public class ERBStationBikeListController {
    public ERBStationBikeListController() {

    }

    public JPanel getBikeListPage() {
        ADataHomePageController<Bike> controller = new UserStationBikeListPageController();
        return controller.getHomePagePane();
    }
}
