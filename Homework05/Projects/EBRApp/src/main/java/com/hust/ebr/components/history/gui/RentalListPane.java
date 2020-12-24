package com.hust.ebr.components.history.gui;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.history.controller.AdminHistoryPageController;

public class RentalListPane extends ADataListPane<Rental> {

    public RentalListPane(ADataPageController<Rental> adminHistoryPageController) {
        this.homePageController = adminHistoryPageController;
    }

    @Override
    public void decorateSinglePane(ADataSinglePane<Rental> singlePane) {

    }
}
