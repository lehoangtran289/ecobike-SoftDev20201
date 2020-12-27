package com.hust.ebr.serverapi.test.component.dockingstation.test;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.components.dockingstation.controller.UserStationPageController;
import com.hust.ebr.serverapi.DockingStationApi;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class UserStationPageControllerTest {
    private UserStationPageController controller = new UserStationPageController(DockingStationApi.singleton());

    @Test
    public void testSearch() {
        Map<String, String> params = new HashMap<>();
        String addr = "1 Dai Co Viet";
        params.put("address", addr);

        List<DockingStation> stationList = (List<DockingStation>) controller.search(params);
        assertEquals("Error in getStation API", stationList.get(0).getStationAddress(), addr);

        String id = "ds1";
        params.clear();
        params.put("id", id);
        stationList = (List<DockingStation>) controller.search(params);
        assertEquals("Error in getStation API", stationList.get(0).getId(), id);

        params.put("address", addr);
        stationList = (List<DockingStation>) controller.search(params);
        assertEquals("Error in getStation API", stationList.get(0).getId(), id);
        assertEquals("Error in getStation API", stationList.get(0).getStationAddress(), addr);

        String name = "Docking Station 1";
        params.clear();
        params.put("stationName", name);
        stationList = (List<DockingStation>) controller.search(params);
        assertEquals("Error in getStation API", stationList.get(0).getStationName(), name);
    }

}
