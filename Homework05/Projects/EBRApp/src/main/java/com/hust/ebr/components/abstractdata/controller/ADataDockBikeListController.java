package com.hust.ebr.components.abstractdata.controller;

import com.hust.ebr.components.abstractdata.gui.ADataBikeListPagePane;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public abstract class ADataDockBikeListController<T> {
    private final ADataBikeListPagePane<T> bikeListPane;

    public ADataDockBikeListController() {
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();

        searchPane.setSearchController(searchParams -> {
            List<? extends T> list = ADataDockBikeListController.this.search(searchParams);
            listPane.updateData(list);
        });

        searchPane.fireSearchEvent();

        bikeListPane = new ADataBikeListPagePane<T>(searchPane, listPane);
    }

    protected abstract List<? extends T> search(Map<String, String> searchParams);

    public JPanel getBikeListPane() {
        return bikeListPane;
    }

    public abstract ADataSearchPane createSearchPane();

    public abstract ADataSinglePane<T> createSinglePane();

    public abstract ADataListPane<T> createListPane();
}
