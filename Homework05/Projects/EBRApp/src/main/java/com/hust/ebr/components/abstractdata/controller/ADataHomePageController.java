package com.hust.ebr.components.abstractdata.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.gui.ADataHomePagePane;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public abstract class ADataHomePageController<T> {
    private final ADataHomePagePane<T> homePagePane;

    public ADataHomePageController() {
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();

        searchPane.setSearchController(searchParams -> {
            List<? extends T> list = ADataHomePageController.this.search(searchParams);
            listPane.updateData(list);
        });

        searchPane.fireSearchEvent();

        homePagePane = new ADataHomePagePane<T>(searchPane, listPane);
    }

    public ADataHomePageController(List<T> list) {
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();
        listPane.updateData(list);

        searchPane.setSearchController(searchParams -> {
            List<? extends T> lst = ADataHomePageController.this.search(searchParams);
            listPane.updateData(lst);
        });

        homePagePane = new ADataHomePagePane<T>(searchPane, listPane);
    }

    public JPanel getHomePagePane() {
        return homePagePane;
    }

    public abstract ADataSearchPane createSearchPane();

    public abstract List<? extends T> search(Map<String, String> searchParams);

    public abstract ADataSinglePane<T> createSinglePane();

    public abstract ADataListPane<T> createListPane();
}
