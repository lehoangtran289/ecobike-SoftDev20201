package com.hust.ebr.editbike;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditBikeWhiteBoxTest {

    private final IBikeApi bApi = BikeApi.singleton();
    private final IDockingStationApi dApi = DockingStationApi.singleton();

    @Test
    public void testUpdateBike() {
        List<Bike> bikeList = bApi.getAllBikes();
        assertTrue("Error in getAllBike API", bikeList.size() > 0);

        Bike bike = bikeList.get(0);
        long cost = 511;
        bike.setCost(cost);
        bApi.updateBike(bike);
        System.out.println(cost == bike.getCost());
        assertEquals("Error in updateBike", cost, bike.getCost(), 0.0001);

        String stationId = "ds2";
        bike.setDockingStationId(stationId);
        bApi.updateBike(bike);
        assertEquals("Error in updateBike API", stationId, bike.getDockingStationId());
    }
}
