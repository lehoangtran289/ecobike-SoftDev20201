package com.hust.ebr.components.bikeList.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataDockBikeListController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bikeList.gui.DockBikeListSearchPane;
import com.hust.ebr.components.bikeList.gui.DockBikeListSinglePane;
import com.hust.ebr.components.bikeList.gui.UserDockBikeListPage;
import com.hust.ebr.serverapi.BikeApi;

import java.util.List;
import java.util.Map;

public class UserDockBikeListPageController extends ADataDockBikeListController<Bike> {
    @Override
    public ADataSearchPane createSearchPane() {
        return new DockBikeListSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        return null;
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new DockBikeListSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new UserDockBikeListPage(this);
    }
}
