package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReadOnlyBikeApiTest {
    private final IBikeApi bApi = BikeApi.singleton();

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
        assertTrue("Error in getBikes API if searchParams = null", bikeList.size() > 0);

        String types = "normalBike";
        searchParams.put("types", types);
        bikeList = bApi.getBikes(searchParams);
        assertEquals("Error in getBikes API", 3, bikeList.size());

        String stationId = "ds1";
        searchParams.put("dockingStationId", stationId);
        bikeList = bApi.getBikes(searchParams);
        assertEquals("Error in getBikes API", 2,  bikeList.size());
    }
}
