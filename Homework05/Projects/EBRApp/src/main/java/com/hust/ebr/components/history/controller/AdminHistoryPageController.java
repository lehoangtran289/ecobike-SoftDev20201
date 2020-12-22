package com.hust.ebr.components.history.controller;

import com.hust.ebr.components.abstractdata.controller.ADataHomePageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

import java.util.List;
import java.util.Map;

public class AdminHistoryPageController extends ADataHomePageController<> {
    @Override
    public ADataSearchPane createSearchPane() {
        return null;
    }

    @Override
    public ADataSinglePane createSinglePane() {
        return null;
    }

    @Override
    public ADataListPane createListPane() {
        return null;
    }

    @Override
    public List search(Map searchParams) {
        return null;
    }
}
