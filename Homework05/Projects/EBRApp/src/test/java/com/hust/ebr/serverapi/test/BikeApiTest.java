package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.serverapi.BikeApi;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BikeApiTest {
    BikeApi bApi = new BikeApi();

    @Test
    public void testGetAllBike() {
        List<Bike> bikeList = bApi.getAllBikes();
        assertEquals("Error in getAllBike API", bikeList.size(), 9);
    }
}
