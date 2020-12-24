package com.hust.ebr.app.user;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.dockingstation.controller.UserStationPageController;

import javax.swing.*;

public class EBRUserController {

    public EBRUserController() {

    }

    public JPanel getHomePage() {
        ADataPageController<DockingStation> controller = new UserStationPageController();
        return controller.getDataPagePane();
    }
}
