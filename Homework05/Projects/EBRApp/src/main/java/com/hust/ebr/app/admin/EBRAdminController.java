package com.hust.ebr.app.admin;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.bike.controller.AdminBikePageController;
import com.hust.ebr.components.dockingstation.controller.AdminStationPageController;
import com.hust.ebr.components.history.controller.AdminHistoryPageController;

import javax.swing.*;

public class EBRAdminController {

    public EBRAdminController() {

    }

    public JPanel getStationPage() {
        ADataHomePageController<DockingStation> controller = new AdminStationPageController();
        return controller.getHomePagePane();
    }

    public JPanel getBikePage() {
        ADataHomePageController<Bike> controller = new AdminBikePageController();
        return controller.getHomePagePane();
    }

    public JPanel getHistoryPage() {
        ADataHomePageController<> controller = new AdminHistoryPageController();
        return controller.getHomePagePane();
    }
}
