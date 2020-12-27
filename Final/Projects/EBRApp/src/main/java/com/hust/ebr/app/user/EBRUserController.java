package com.hust.ebr.app.user;

import com.hust.ebr.factory.UserPageFactory;

import javax.swing.*;

public class EBRUserController {

    public EBRUserController() {

    }

    public JPanel getPage(String type) {
        return UserPageFactory.singleton().createPage(type).getDataPagePane();
    }

    /**
     * public JPanel getHomePage() {
     *         ADataPageController<DockingStation> controller = new UserStationPageController
     *         (DockingStationApi.singleton());
     *         return controller.getDataPagePane();
     *     }
     */
}
