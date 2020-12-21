package com.hust.ebr.components.dockingstation.controller;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSearchPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSinglePane;
import com.hust.ebr.components.dockingstation.gui.UserDockingStationListPane;
import com.hust.ebr.serverapi.DockingStationApi;

public class UserHomePageController extends ADataHomePageController<DockingStation> {

    public UserHomePageController() {
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
        return new UserDockingStationListPane(this);
    }

    @Override
    public DockingStation search(String searchParams) {
        return new DockingStationApi().getStationById(searchParams);
    }
}
