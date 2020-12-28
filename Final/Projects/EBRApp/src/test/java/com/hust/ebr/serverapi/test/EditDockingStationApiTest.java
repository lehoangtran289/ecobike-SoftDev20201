package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EditDockingStationApiTest {
    private final IDockingStationApi dsAPI = DockingStationApi.singleton();
    private final IBikeApi bApi = BikeApi.singleton();

    @Test
    public void testUpdateStation() {

        List<DockingStation> stationList = dsAPI.getStations(null);
        assertTrue("Error in getStation API", stationList.size() > 0);
        DockingStation station = stationList.get(0);
        String newAddr = "2 Dai Co Viet";
        station.setStationAddress(newAddr);
        dsAPI.updateStation(station);
        assertEquals("Error in updateStation API", station.getStationAddress(), newAddr);
    }

    @Test
    public void testDeleteStation() {
        List<DockingStation> stationList = dsAPI.getStations(null);
        assertTrue("Error in getStation API", stationList.size() > 0);

        DockingStation station = stationList.get(0);
        List<String> bikeList = station.getBikeIds();
        String deletedStationId = station.getId();

        boolean isDeleted = dsAPI.deleteStation(deletedStationId);
        stationList = dsAPI.getStations(null);

        assertEquals("Error in deleteStation API", isDeleted, true);
        assertFalse("Error in deleteStation API", stationList.contains(station));
        assertEquals("Error in deleteStation API", bApi.getBikeById(bikeList.get(0)).getDockingStationId(), null);
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
        assertTrue("Error in addStation API", stationList.contains(station));
    }
}
