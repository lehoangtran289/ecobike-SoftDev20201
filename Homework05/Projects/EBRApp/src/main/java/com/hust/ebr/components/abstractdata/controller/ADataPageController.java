package com.hust.ebr.components.abstractdata.controller;

import com.hust.ebr.components.abstractdata.gui.ADataPagePane;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.serverapi.abstractdata.IServerApi;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public abstract class ADataPageController<T> {
    private final ADataPagePane<T> dataPagePane;
    private final IServerApi serverApi;

    public ADataPageController(IServerApi serverApi) {
        this.serverApi = serverApi;
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();

        searchPane.setSearchController(searchParams -> {
            List<? extends T> list = ADataPageController.this.search(searchParams);
            listPane.updateData(list);
        });

        searchPane.fireSearchEvent();

        dataPagePane = new ADataPagePane<T>(searchPane, listPane);
    }

    public ADataPageController(IServerApi serverApi, List<T> list) {
        this.serverApi = serverApi;
        ADataSearchPane searchPane = createSearchPane();
        ADataListPane<T> listPane = createListPane();
        listPane.updateData(list);

        searchPane.setSearchController(searchParams -> {
            List<? extends T> lst = ADataPageController.this.search(searchParams);
            listPane.updateData(lst);
        });

        dataPagePane = new ADataPagePane<T>(searchPane, listPane);
    }

    public JPanel getDataPagePane() {
        return dataPagePane;
    }

    public IServerApi getServerApi() {
        return serverApi;
    }

    public abstract ADataSearchPane createSearchPane();

    public abstract List<? extends T> search(Map<String, String> searchParams);

    public abstract ADataSinglePane<T> createSinglePane();

    public abstract ADataListPane<T> createListPane();
}
