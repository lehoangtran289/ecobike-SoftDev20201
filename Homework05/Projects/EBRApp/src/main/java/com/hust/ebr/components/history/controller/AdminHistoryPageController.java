package com.hust.ebr.components.history.controller;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.history.gui.RentalListPane;
import com.hust.ebr.components.history.gui.RentalSearchPane;
import com.hust.ebr.components.history.gui.RentalSinglePane;
import com.hust.ebr.serverapi.RentalApi;

import java.util.List;
import java.util.Map;

public class AdminHistoryPageController extends ADataPageController<Rental> {
    public AdminHistoryPageController() {
        super();
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new RentalSearchPane();
    }

    @Override
    public List<? extends Rental> search(Map<String, String> searchParams) {
        return new RentalApi().getRentals(searchParams);
    }

    @Override
    public ADataSinglePane<Rental> createSinglePane() {
        return new RentalSinglePane();
    }

    @Override
    public ADataListPane<Rental> createListPane() {
        return new RentalListPane(this);
    }
}
