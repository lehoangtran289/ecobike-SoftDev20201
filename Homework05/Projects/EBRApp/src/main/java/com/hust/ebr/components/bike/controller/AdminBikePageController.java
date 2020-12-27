package com.hust.ebr.components.bike.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.controller.IDataUpdateController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.bike.gui.AdminBikeListPane;
import com.hust.ebr.components.bike.gui.BikeEditDialog;
import com.hust.ebr.components.bike.gui.BikeSearchPane;
import com.hust.ebr.components.bike.gui.BikeSinglePane;
import com.hust.ebr.factory.AdminPageFactory;
import com.hust.ebr.factory.UserPageFactory;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class AdminBikePageController extends ADataPageController<Bike> {
    static {
        AdminPageFactory.singleton().registerPage("adminBikePage", new AdminBikePageController(BikeApi.singleton()));
    }

    @Override
    public AdminBikePageController createPageController() {
        return new AdminBikePageController(BikeApi.singleton());
    }

    public AdminBikePageController(IBikeApi bikeApi) {
        super(bikeApi);
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new BikeSearchPane();
    }

    @Override
    public List<? extends Bike> search(Map<String, String> searchParams) {
        return ((BikeApi) getServerApi()).getBikes(searchParams);
    }

    @Override
    public ADataSinglePane<Bike> createSinglePane() {
        return new BikeSinglePane();
    }

    @Override
    public ADataListPane<Bike> createListPane() {
        return new AdminBikeListPane(this);
    }

    public Bike updateBike(Bike bike) {
        return ((BikeApi) getServerApi()).updateBike(bike);
    }

    public void onEdit(ADataSinglePane<Bike> singlePane, JButton editButton) {
        IDataUpdateController<Bike> manageController = new IDataUpdateController<Bike>() {
            @Override
            public void onAct(Bike bike) {
                Bike newBike = AdminBikePageController.this.updateBike(bike);
                singlePane.updateData(newBike);
            }
        };

        editButton.addActionListener(e -> {
            new BikeEditDialog(singlePane.getData(), manageController);
        });
    }

}
