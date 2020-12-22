package com.hust.ebr.app.user;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.dockingstation.controller.UserStationPageController;

import javax.swing.*;

public class EBRUserController {

    public EBRUserController() {

    }

    public JPanel getHomePage() {
        ADataHomePageController<DockingStation> controller = new UserStationPageController();
        return controller.getHomePagePane();
    }
}
