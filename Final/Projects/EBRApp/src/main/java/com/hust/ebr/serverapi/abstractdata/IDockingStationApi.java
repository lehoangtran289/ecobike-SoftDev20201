package com.hust.ebr.serverapi.abstractdata;

import com.hust.ebr.beans.DockingStation;

import java.util.List;
import java.util.Map;

public interface IDockingStationApi extends IServerApi{
    String PATH = "http://localhost:8080/api/docking-stations";

    List<DockingStation> getStations(Map<String, String> params);
    DockingStation getStationById(String id);
    DockingStation updateStation(DockingStation station);
    DockingStation addStation(DockingStation station);
    boolean deleteStation(String id);
}
