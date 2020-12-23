package com.hust.ebr.components.bike.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.gui.AdminBikeListPane;
import com.hust.ebr.components.bike.gui.BikeSearchPane;
import com.hust.ebr.components.bike.gui.BikeSinglePane;
import com.hust.ebr.serverapi.BikeApi;

import java.util.List;
import java.util.Map;

public class AdminBikePageController extends ADataPageController<Bike> {

    public AdminBikePageController() {
        super();
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new BikeSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        return new BikeApi().getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new BikeSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new AdminBikeListPane(this);
    }

    public Bike updateBike(Bike bike) {
        return new BikeApi().updateBike(bike);
    }
}
