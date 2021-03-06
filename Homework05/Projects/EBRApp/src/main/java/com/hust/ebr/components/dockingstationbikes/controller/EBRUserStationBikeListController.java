package com.hust.ebr.components.dockingstationbikes.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.dockingstationbikes.abstractdata.AStationBikeListController;
import com.hust.ebr.serverapi.BikeApi;

import javax.swing.*;
import java.util.List;

public class EBRUserStationBikeListController extends AStationBikeListController {
    public EBRUserStationBikeListController() {
        super();
    }

    @Override
    public JPanel getBikeListPage(String stationID, List<Bike> bikeList) {
        ADataPageController<Bike> controller = new UserStationBikeListPageController(BikeApi.singleton(), stationID, bikeList);
        return controller.getDataPagePane();
    }
}
