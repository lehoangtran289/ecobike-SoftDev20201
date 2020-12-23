package com.hust.ebr.app.bikeListScreen;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.bikeList.controller.UserStationBikeListPageController;

import javax.swing.*;
import java.util.List;

public class ERBStationBikeListController {
    public ERBStationBikeListController() {

    }

    public JPanel getBikeListPage(String stationID, List<Bike> bikeList) {
        ADataHomePageController<Bike> controller = new UserStationBikeListPageController(stationID, bikeList);
        return controller.getHomePagePane();
    }
}
