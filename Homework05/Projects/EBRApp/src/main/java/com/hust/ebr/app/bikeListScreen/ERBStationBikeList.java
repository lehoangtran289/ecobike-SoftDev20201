package com.hust.ebr.app.bikeListScreen;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.BikeApi;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class ERBStationBikeList extends JFrame {
    public ERBStationBikeList(ERBStationBikeListController controller, DockingStation station) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        List<String> bikeIdList = new ArrayList<String>();
        bikeIdList = station.getBikeIds();
        List<Bike> bikeList = new ArrayList<Bike>();
        for (String id: bikeIdList) {
            bikeList.add(new BikeApi().getBikeById(id));
        }

        JPanel dockBikeListPage = controller.getBikeListPage(station.getId(), bikeList);
        rootPanel.add(dockBikeListPage);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("List of bike in " + station.getStationName());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
}
