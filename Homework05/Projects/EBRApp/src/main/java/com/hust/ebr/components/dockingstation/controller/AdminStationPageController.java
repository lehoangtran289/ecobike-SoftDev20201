package com.hust.ebr.components.dockingstation.controller;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.gui.AdminStationListPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSearchPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSinglePane;
import com.hust.ebr.serverapi.DockingStationApi;

import java.util.List;
import java.util.Map;

public class AdminStationPageController extends ADataHomePageController<DockingStation> {

    public AdminStationPageController() {
        super();
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new DockingStationSearchPane();
    }

    @Override
    public List<? extends DockingStation> search(Map<String, String> searchParams) {
        return new DockingStationApi().getStations(searchParams);
    }

    @Override
    public ADataSinglePane<DockingStation> createSinglePane() {
        return new DockingStationSinglePane();
    }

    @Override
    public ADataListPane<DockingStation> createListPane() {
        return new AdminStationListPane(this);
    }

    public DockingStation updateDockingStation(DockingStation dockingStation) {
        return new DockingStationApi().updateStation(dockingStation);
    }
}