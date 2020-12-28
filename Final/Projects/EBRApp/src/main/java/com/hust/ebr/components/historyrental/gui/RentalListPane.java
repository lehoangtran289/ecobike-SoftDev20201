package com.hust.ebr.components.historyrental.gui;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;

public class RentalListPane extends ADataListPane<Rental> {

    public RentalListPane(ADataPageController<Rental> adminHistoryPageController) {
        this.dataPageController = adminHistoryPageController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Rental> singlePane) {

    }
}
