package com.hust.ebr.components.abstractdata.controller;

import com.hust.ebr.components.abstractdata.gui.ADataHomePagePane;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;

public abstract class ADataHomePageController<T> {
    private final ADataHomePagePane<T> homePagePane;

    public ADataHomePageController() {
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();

        searchPane.setSearchController(searchParams -> {
            T object = ADataHomePageController.this.search(searchParams);
            listPane.updateData(object);
        });

        homePagePane = new ADataHomePagePane<T>(searchPane, listPane);
    }

    public JPanel getHomePagePane() {
        return homePagePane;
    }

    public abstract ADataSearchPane createSearchPane();

    public abstract T search(String searchParams);

    public abstract ADataSinglePane<T> createSinglePane();

    public abstract ADataListPane<T> createListPane();
}
