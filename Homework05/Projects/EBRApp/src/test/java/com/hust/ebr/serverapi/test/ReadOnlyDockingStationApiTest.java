package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReadOnlyDockingStationApiTest {

    private final IDockingStationApi dsAPI = DockingStationApi.singleton();

    @Test
    public void testGetStations() {
        Map<String, String> searchParams = new HashMap<>() ;
        List<DockingStation> stationList = dsAPI.getStations(null);
        assertEquals("Error in getStation API", stationList.size(), 3);

        String id = "ds1";
        searchParams.put("id", id);
        stationList = dsAPI.getStations(searchParams);
        assertEquals("Error in getStation API", stationList.get(0).getId(), id);

        String addr = "1 Dai Co Viet";
        searchParams.put("address", addr);
        stationList = dsAPI.getStations(searchParams);
        assertEquals("Error in getStation API", stationList.get(0).getStationAddress(), addr);
    }

    @Test
    public void testGetStationById() {
        DockingStation station;
        String id = "wrong id";

        station = dsAPI.getStationById(id);
        assertEquals("Error in getStationById API if can not find any station", station, null);

        id = "ds2";
        station = dsAPI.getStationById(id);
        assertEquals("Error in getStationById API", station.getId(), id);
    }
}
