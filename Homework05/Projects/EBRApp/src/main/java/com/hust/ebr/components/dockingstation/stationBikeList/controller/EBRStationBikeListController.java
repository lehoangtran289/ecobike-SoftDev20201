package com.hust.ebr.components.dockingstation.stationBikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.dockingstation.stationBikeList.controller.UserStationBikeListPageController;

import javax.swing.*;
import java.util.List;

public class EBRStationBikeListController {
    public EBRStationBikeListController() {

    }

    public JPanel getBikeListPage(String stationID, List<Bike> bikeList) {
        ADataHomePageController<Bike> controller = new UserStationBikeListPageController(stationID, bikeList);
        return controller.getHomePagePane();
    }
}
