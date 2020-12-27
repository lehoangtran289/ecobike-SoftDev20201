package com.hust.ebr.components.dockingstationbikes.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.dockingstationbikes.abstractdata.AStationBikeListController;
import com.hust.ebr.serverapi.BikeApi;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRStationBikeList extends JDialog {
    public EBRStationBikeList(AStationBikeListController controller, DockingStation station) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        List<Bike> bikes = station.getBikeIds().stream()
                .map(id -> BikeApi.singleton().getBikeById(id))
                .collect(Collectors.toList());
        JPanel stationBikeListPage = controller.getBikeListPage(station.getId(), bikes);
        rootPanel.add(stationBikeListPage);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
//        setModal(true);
        setTitle("List of bike in " + station.getStationName());
        setVisible(true);
    }
}
