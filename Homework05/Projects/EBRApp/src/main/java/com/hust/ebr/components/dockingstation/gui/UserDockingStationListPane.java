package com.hust.ebr.components.dockingstation.gui;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

public class UserDockingStationListPane extends ADataListPane<DockingStation> {

    public UserDockingStationListPane(ADataHomePageController<DockingStation> homePageController) {
        this.homePageController = homePageController;
    }
    @Override
    public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {

    }
}
