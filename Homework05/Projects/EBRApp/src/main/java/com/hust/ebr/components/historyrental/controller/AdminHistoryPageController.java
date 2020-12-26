package com.hust.ebr.components.historyrental.controller;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.historyrental.gui.RentalListPane;
import com.hust.ebr.components.historyrental.gui.RentalSearchPane;
import com.hust.ebr.components.historyrental.gui.RentalSinglePane;
import com.hust.ebr.serverapi.RentalApi;
import com.hust.ebr.serverapi.abstractdata.IRentalApi;

import java.util.List;
import java.util.Map;

public class AdminHistoryPageController extends ADataPageController<Rental> {
    public AdminHistoryPageController(IRentalApi rentalApi) {
        super(rentalApi);
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new RentalSearchPane();
    }

    @Override
    public List<? extends Rental> search(Map<String, String> searchParams) {
        return ((RentalApi) getServerApi()).getRentals(searchParams);
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
