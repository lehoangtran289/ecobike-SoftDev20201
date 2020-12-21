package com.hust.ebr.app.user;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.dockingstation.controller.UserHomePageController;

import javax.swing.*;

public class EBRUserController {

    public EBRUserController() {

    }

    public JPanel getHomePage() {
        ADataHomePageController<DockingStation> controller = new UserHomePageController();
        return controller.getHomePagePane();
    }
}
