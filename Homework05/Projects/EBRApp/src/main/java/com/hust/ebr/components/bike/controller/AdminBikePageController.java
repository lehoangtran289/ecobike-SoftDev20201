package com.hust.ebr.components.bike.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import java.util.List;
import java.util.Map;

public class AdminBikePageController extends ADataHomePageController<Bike> {
    @Override
    public ADataSearchPane createSearchPane() {
        return null;
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        return null;
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return null;
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return null;
    }
}
