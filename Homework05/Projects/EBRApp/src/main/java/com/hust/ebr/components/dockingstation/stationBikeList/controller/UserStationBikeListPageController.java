package com.hust.ebr.components.dockingstation.stationBikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.stationBikeList.gui.StationBikeListSearchPane;
import com.hust.ebr.components.dockingstation.stationBikeList.gui.StationBikeListSinglePane;
import com.hust.ebr.components.dockingstation.stationBikeList.gui.UserStationBikeListPane;
import com.hust.ebr.serverapi.BikeApi;

import java.util.List;
import java.util.Map;

public class UserStationBikeListPageController extends ADataHomePageController<Bike> {
    private String stationID;

    public UserStationBikeListPageController(String stationID, List<Bike> list) {
        super(list);
        this.stationID = stationID;
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new StationBikeListSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        searchParams.put("dockingStationId", this.stationID);
        return new BikeApi().getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new StationBikeListSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new UserStationBikeListPane(this);
    }
}
