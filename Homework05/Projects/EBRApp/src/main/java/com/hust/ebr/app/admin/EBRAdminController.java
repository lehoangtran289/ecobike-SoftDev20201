package com.hust.ebr.app.admin;

import com.hust.ebr.factory.AdminPageFactory;

import javax.swing.*;

public class EBRAdminController {

    public EBRAdminController() {

    }

    public JPanel getPage(String type) {
        return AdminPageFactory.singleton().createPage(type).getDataPagePane();
    }

    /**
     *
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
     */
}
