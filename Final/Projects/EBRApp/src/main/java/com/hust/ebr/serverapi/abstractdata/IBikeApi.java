package com.hust.ebr.serverapi.abstractdata;

import com.hust.ebr.beans.Bike;

import java.util.List;
import java.util.Map;

public interface IBikeApi extends IServerApi {
    String PATH = "http://localhost:8080/api/bikes";

    List<Bike> getAllBikes();
    Bike getBikeById(String id);
    List<Bike> getBikes(Map<String, String> params);
    Bike updateBike(Bike bike);
    Bike addBike(Bike bike);
    boolean deleteBike(String id);
}
