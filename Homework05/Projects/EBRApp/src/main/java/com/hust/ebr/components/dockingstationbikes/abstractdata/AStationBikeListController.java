package com.hust.ebr.components.dockingstationbikes.abstractdata;

import com.hust.ebr.beans.Bike;

import javax.swing.*;
import java.util.List;

public abstract class AStationBikeListController {
    public AStationBikeListController() {
    }

    public abstract JPanel getBikeListPage(String stationID, List<Bike> bikeList);
}
