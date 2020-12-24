package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.DockingStationApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DockingStationApiTest {

    DockingStationApi dsAPI = new DockingStationApi();

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

    @Test
    public void testUpdateStation() {

        List<DockingStation> stationList = dsAPI.getStations(null);
        assertEquals("Error in getStation API", stationList.size(), 3);

        DockingStation station = stationList.get(0);
        String newAddr = "2 Dai Co Viet";
        station.setStationAddress(newAddr);
        dsAPI.updateStation(null);
        assertEquals("Error in updateStation API", station.getStationAddress(), newAddr);
    }

    @Test
    public void testDeleteStation() {
        List<DockingStation> stationList = dsAPI.getStations(null);
        assertEquals("Error in getStation API", stationList.size(), 3);

        DockingStation station = stationList.get(0);
        String deletedStationId = station.getId();

        boolean isDeleted = dsAPI.deleteStation(deletedStationId);
        stationList = dsAPI.getStations(null);

        assertEquals("Error in deleteStation API", isDeleted, true);
        assertEquals("Error in deleteStation API", stationList.size(), 2);
    }

    @Test
    public void testAddStation() {
        List<String> bikeIds = new ArrayList<>();
        DockingStation station = new DockingStation();
        station.setId("ds_test");
        station.setStationName("test station");
        station.setStationAddress("1 Pho Hue");
        station.setEBikeCount(2);
        station.setTwinBikeCount(2);
        station.setNormalBikeCount(2);
        station.setTotalDockCount(10);
        station.setEmptyDockCount(4);
        station.setBikeIds(bikeIds);

        dsAPI.addStation(station);
        List<DockingStation> stationList = dsAPI.getStations(null);
        assertEquals("Error in addStation API", stationList.size(), 4);
    }
}
