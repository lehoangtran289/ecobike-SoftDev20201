package com.hust.ebr.app.admin;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.bike.controller.AdminBikePageController;
import com.hust.ebr.components.dockingstation.controller.AdminStationPageController;
import com.hust.ebr.components.historyrental.controller.AdminHistoryPageController;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.RentalApi;

import javax.swing.*;

public class EBRAdminController {

    public EBRAdminController() {

    }

    public JPanel getStationPage() {
        ADataPageController<DockingStation> controller = new AdminStationPageController(DockingStationApi.singleton());
        return controller.getDataPagePane();
    }

    public JPanel getBikePage() {
        ADataPageController<Bike> controller = new AdminBikePageController(BikeApi.singleton());
        return controller.getDataPagePane();
    }

    public JPanel getHistoryPage() {
        ADataPageController<Rental> controller = new AdminHistoryPageController(RentalApi.singleton());
        return controller.getDataPagePane();
    }
}
