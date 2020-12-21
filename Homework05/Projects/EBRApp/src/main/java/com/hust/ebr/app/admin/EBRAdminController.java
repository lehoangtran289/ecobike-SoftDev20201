package com.hust.ebr.app.admin;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.dockingstation.controller.AdminHomePageController;

import javax.swing.*;

public class EBRAdminController {

    public EBRAdminController() {

    }

    public JPanel getHomePage() {
        ADataHomePageController<DockingStation> controller = new AdminHomePageController();
        return controller.getHomePagePane();
    }
}
