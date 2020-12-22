package com.hust.ebr.components.bikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bikeList.gui.StationBikeListSearchPane;
import com.hust.ebr.components.bikeList.gui.StationBikeListSinglePane;
import com.hust.ebr.components.bikeList.gui.UserStationBikeListPage;
import com.hust.ebr.serverapi.BikeApi;

import java.util.List;
import java.util.Map;

public class UserStationBikeListPageController extends ADataHomePageController<Bike> {
    @Override
    public ADataSearchPane createSearchPane() {
        return new StationBikeListSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        return new BikeApi().getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new StationBikeListSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new UserStationBikeListPage(this);
    }
}
