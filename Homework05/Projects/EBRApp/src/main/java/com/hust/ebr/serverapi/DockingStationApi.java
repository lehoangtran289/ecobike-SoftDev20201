package com.hust.ebr.serverapi;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.serverapi.abstractdata.IDockingStationApi;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DockingStationApi implements IDockingStationApi {
    public DockingStationApi() {
    }

    @Override
    public List<DockingStation> getStations(Map<String, String> params) {
        WebTarget webTarget = client.target(PATH);
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet())
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
        }
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<DockingStation> result = response.readEntity(new GenericType<List<DockingStation>>() {
        });
        System.out.println("getStations: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public DockingStation getStationById(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        DockingStation result = response.readEntity(DockingStation.class);
        System.out.println("getStationById: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public DockingStation updateStation(DockingStation station) {
        String id = Optional.ofNullable(station.getId()).orElse("");
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(station, MediaType.APPLICATION_JSON));
        System.out.println("updateStation: " + response);
        return response.getStatus() == 200 ? response.readEntity(DockingStation.class) : null;
    }

    @Override
    public DockingStation addStation(DockingStation station) {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(station, MediaType.APPLICATION_JSON));
        System.out.println("addStation: " + response);
        return response.getStatus() == 200 ? response.readEntity(DockingStation.class) : null;
    }

    @Override
    public boolean deleteStation(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        System.out.println("deleteStation: " + response);
        return response.getStatus() == 200;
    }
}
