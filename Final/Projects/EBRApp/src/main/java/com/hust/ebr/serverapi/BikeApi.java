package com.hust.ebr.serverapi;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BikeApi implements IBikeApi {
    private static final IBikeApi singleton = new BikeApi();

    private BikeApi() {
    }

    public static IBikeApi singleton() {
        return singleton;
    }

    @Override
    public List<Bike> getAllBikes() {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        List<Bike> result = response.readEntity(new GenericType<ArrayList<Bike>>() {
        });
        System.out.println("getAllBikes: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public Bike getBikeById(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Bike result = response.readEntity(Bike.class);
        System.out.println("getBikeById: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public List<Bike> getBikes(Map<String, String> params) {
        WebTarget webTarget = client.target(PATH);
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet())
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
        }
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<Bike> result = response.readEntity(new GenericType<List<Bike>>() {
        });
        System.out.println("getBikes: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public Bike updateBike(Bike bike) {
        String id = Optional.ofNullable(bike.getId()).orElse("");
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(bike, MediaType.APPLICATION_JSON));
        System.out.println("updateBike: " + response);
        return response.getStatus() == 200 ? response.readEntity(Bike.class) : null;
    }

    @Override
    public Bike addBike(Bike bike) {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(bike, MediaType.APPLICATION_JSON));
        System.out.println("addBike: " + response);
        return response.getStatus() == 200 ? response.readEntity(Bike.class) : null;
    }

    @Override
    public boolean deleteBike(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println("deleteBike: " + response);
        return response.getStatus() == 200;
    }

}
