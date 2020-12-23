package com.hust.ebr.components.dockingstation.stationBikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.dockingstation.stationBikeList.abstractdata.AStationBikeListController;

import javax.swing.*;
import java.util.List;

public class EBRAdminStationBikeListController extends AStationBikeListController {
    public EBRAdminStationBikeListController() {
        super();
    }

    @Override
    public JPanel getBikeListPage(String stationID, List<Bike> bikeList) {
        ADataPageController<Bike> controller = new AdminStationBikeListPageController(stationID, bikeList);
        return controller.getDataPagePane();
    }
}
