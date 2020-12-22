package com.hust.ebr.components.dockingstation.controller;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.gui.AdminDockingStationListPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSearchPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSinglePane;
import com.hust.ebr.serverapi.DockingStationApi;

import java.util.List;
import java.util.Map;

public class AdminHomePageController extends ADataHomePageController<DockingStation> {

    public AdminHomePageController() {
        super();
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new DockingStationSearchPane();
    }

    @Override
    public ADataSinglePane createSinglePane() {
        return new DockingStationSinglePane();
    }

    @Override
    public ADataListPane createListPane() {
        return new AdminDockingStationListPane(this);
    }

    @Override
    public List<? extends DockingStation> search(Map<String, String> searchParams) {
        return new DockingStationApi().getStations(searchParams);
    }

    public DockingStation updateDockingStation(DockingStation dockingStation) {
        return new DockingStationApi().updateStation(dockingStation);
    }
}
