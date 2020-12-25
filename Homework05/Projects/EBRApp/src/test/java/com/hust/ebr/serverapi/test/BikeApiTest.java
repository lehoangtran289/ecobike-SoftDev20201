package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BikeApiTest {
    BikeApi bApi = new BikeApi();

    @Test
    public void testGetAllBike() {
        List<Bike> bikeList = bApi.getAllBikes();
        assertEquals("Error in getAllBike API", 9, bikeList.size());
    }

    @Test
    public void testGetBikeById() {
        String bikeId = "wrong_id";
        Bike bike = bApi.getBikeById(bikeId);
        assertEquals("Error in getBikeById API if can not find any bike", null, bike);

        bikeId = "nb2";
        bike = bApi.getBikeById(bikeId);
        assertEquals("Error in getBikeById API", bikeId, bike.getId());
    }

    @Test
    public void testGetBikes() {
        Map<String, String> searchParams = new HashMap<>();
        List<Bike> bikeList = bApi.getBikes(null);
        assertEquals("Error in getBikes API if searchParams = null", 9, bikeList.size());

        String types = "normalBike";
        searchParams.put("types", types);
        bikeList = bApi.getBikes(searchParams);
        assertEquals("Error in getBikes API", 3, bikeList.size());

        String stationId = "ds1";
        searchParams.put("dockingStationId", stationId);
        bikeList = bApi.getBikes(searchParams);
        assertEquals("Error in getBikes API", 2,  bikeList.size());
    }

    @Test
    public void testUpdateBike() {
        List<Bike> bikeList = bApi.getAllBikes();
        assertEquals("Error in getAllBike API", 9, bikeList.size());

        Bike bike = bikeList.get(0);
        float cost = 511.25f;
        bike.setCost(cost);
        bApi.updateBike(bike);
        System.out.println(cost == bike.getCost());
        assertEquals("Error in updateBike", cost, bike.getCost(), 0.0001);

        String stationId = "ds2";
        bike.setDockingStationId(stationId);
        bApi.updateBike(bike);
        assertEquals("Error in updateBike API", stationId, bike.getDockingStationId());
    }

    @Test
    public void testAddBike() throws InterruptedException {
        String stationId = "ds1";
        DockingStation station = new DockingStationApi().getStationById(stationId);
        Bike bike = new Bike();
        String bikeId = "test_bike";

        bike.setId(bikeId);
        bike.setDockingStationId(station.getId());
        bApi.addBike(bike);
        assertEquals("Error in addBike API", station.getId(), bike.getDockingStationId());

        station = new DockingStationApi().getStationById(stationId);
        assertEquals("Error in addBike API", true, station.getBikeIds().contains(bike.getId()));
    }

    @Test
    public void testDeleteBike() {
        String stationId = "ds1";
        DockingStation station = new DockingStationApi().getStationById(stationId);
        Bike bike = bApi.getBikeById("nb1"); //station.getBikeIds().get(0));

        List<Bike> bikeList = bApi.getAllBikes();
        int beforeDeleteSize = bikeList.size();

        bApi.deleteBike(bike.getId());
        System.out.println(station.getBikeIds());
        bikeList = bApi.getAllBikes();
        assertEquals("Error in deleteBike API", beforeDeleteSize - 1, bikeList.size());

        station = new DockingStationApi().getStationById(stationId);
        assertEquals("Error in deleteBike API", false, station.getBikeIds().contains(bike.getId()));
    }
}
