package com.hust.ebr.components.dockingstation.controller;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.abstractdata.controller.ADataPageController;
import com.hust.ebr.components.abstractdata.controller.IDataDeleteController;
import com.hust.ebr.components.abstractdata.controller.IDataUpdateController;
import com.hust.ebr.components.abstractdata.gui.ADataListPane;
import com.hust.ebr.components.abstractdata.gui.ADataSearchPane;
import com.hust.ebr.components.abstractdata.gui.ADataSinglePane;
import com.hust.ebr.components.dockingstation.gui.AdminStationListPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationEditDialog;
import com.hust.ebr.components.dockingstation.gui.DockingStationSearchPane;
import com.hust.ebr.components.dockingstation.gui.DockingStationSinglePane;
import com.hust.ebr.factory.AdminPageFactory;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AdminStationPageController extends ADataPageController<DockingStation> {
    static {
        AdminPageFactory.singleton().registerPage("adminStationPage",
                new AdminStationPageController(DockingStationApi.singleton()));
    }

    public AdminStationPageController(IDockingStationApi stationApi) {
        super(stationApi);
    }

    @Override
    public AdminStationPageController createPageController() {
        return new AdminStationPageController(DockingStationApi.singleton());
    }

    @Override
    public ADataSearchPane createSearchPane() {
        return new DockingStationSearchPane();
    }

    @Override
    public List<? extends DockingStation> search(Map<String, String> searchParams) {
        return ((IDockingStationApi) getServerApi()).getStations(searchParams);
    }

    @Override
    public ADataSinglePane<DockingStation> createSinglePane() {
        return new DockingStationSinglePane();
    }

    @Override
    public ADataListPane<DockingStation> createListPane() {
        return new AdminStationListPane(this);
    }

    public DockingStation updateDockingStation(DockingStation dockingStation) {
        return ((IDockingStationApi) getServerApi()).updateStation(dockingStation);
    }

    public boolean deleteDockingStation(DockingStation station) {
        System.out.println("xxxxxxxxxx");
        return ((DockingStationApi) getServerApi()).deleteStation(station.getId()) ;
    }

    public void onEdit(ADataSinglePane<DockingStation> singlePane, JButton editButton) {
        IDataUpdateController<DockingStation> controller = new IDataUpdateController<DockingStation>() {
            @Override
            public void onAct(DockingStation dockingStation) {
                DockingStation newDockingStation =
                        AdminStationPageController.this.updateDockingStation(dockingStation);
                singlePane.updateData(newDockingStation);
            }
        };

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DockingStationEditDialog(singlePane.getData(), controller);
            }
        });
    }

    public void onDelete(ADataSinglePane<DockingStation> singlePane, JButton delButton) {
        IDataDeleteController<DockingStation> delController = new IDataDeleteController<DockingStation>() {
            @Override
            public void onAct(DockingStation station) {
                if (AdminStationPageController.this.deleteDockingStation(station)) {
                    singlePane.updateData(null);
                    singlePane.removeDataHandlingComponent();
                    System.out.println("success");
                }
            }
        };

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delController.onAct(singlePane.getData());
            }
        });
    }
}
