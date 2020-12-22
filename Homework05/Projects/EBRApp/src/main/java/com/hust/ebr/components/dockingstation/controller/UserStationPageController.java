package com.hust.ebr.components.dockingstation.controller;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import java.util.List;
import java.util.Map;

public class UserStationPageController extends ADataHomePageController<DockingStation> {
    @Override
    public ADataSearchPane createSearchPane() {
        return null;
    }

    @Override
    public List<? extends DockingStation> search(Map<String, String> searchParams) {
        return null;
    }

    @Override
    public ADataSinglePane<DockingStation> createSinglePane() {
        return null;
    }

    @Override
    public ADataListPane<DockingStation> createListPane() {
        return null;
    }
}
