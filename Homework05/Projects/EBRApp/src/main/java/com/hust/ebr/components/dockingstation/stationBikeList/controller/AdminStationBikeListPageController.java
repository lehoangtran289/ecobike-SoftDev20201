package com.hust.ebr.components.dockingstation.stationBikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.gui.BikeSearchPane;
import com.hust.ebr.components.bike.gui.BikeSinglePane;
import com.hust.ebr.components.dockingstation.stationBikeList.gui.AdminStationBikeListPane;
import com.hust.ebr.serverapi.BikeApi;

import java.util.List;
import java.util.Map;

public class AdminStationBikeListPageController extends ADataPageController<Bike> {
    private final String stationID;

    public AdminStationBikeListPageController(String stationID, List<Bike> list) {
        super(list);
        this.stationID = stationID;
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new BikeSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        searchParams.put("dockingStationId", this.stationID);
        return new BikeApi().getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new BikeSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new AdminStationBikeListPane(this);
    }
}
