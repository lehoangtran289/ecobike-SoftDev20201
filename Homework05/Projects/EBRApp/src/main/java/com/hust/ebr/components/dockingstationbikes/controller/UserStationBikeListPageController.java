package com.hust.ebr.components.dockingstationbikes.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.gui.BikeSinglePane;
import com.hust.ebr.components.dockingstationbikes.gui.StationBikeSearchPane;
import com.hust.ebr.components.dockingstationbikes.gui.UserStationBikeListPane;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;

import java.util.List;
import java.util.Map;

public class UserStationBikeListPageController extends ADataPageController<Bike> {
    private final String stationID;

    public UserStationBikeListPageController(IBikeApi bikeApi, String stationID, List<Bike> list) {
        super(bikeApi, list);
        this.stationID = stationID;
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new StationBikeSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        searchParams.put("dockingStationId", this.stationID);
        return ((BikeApi) getServerApi()).getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new BikeSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new UserStationBikeListPane(this);
    }
}
