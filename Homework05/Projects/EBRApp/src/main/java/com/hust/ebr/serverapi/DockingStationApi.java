package com.hust.ebr.serverapi;

import com.hust.ebr.beans.DockingStation;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DockingStationApi {
    public static final String PATH = "http://localhost:8080/api/docking-stations";
    private final Client client;

    public DockingStationApi() {
        client = ClientBuilder.newClient();
    }

    public List<DockingStation> getStations(Map<String, String> params) {
        WebTarget webTarget = client.target(PATH);
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet())
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
        }
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<DockingStation> result = response.readEntity(new GenericType<List<DockingStation>>() {
        });
        System.out.println(result);
        return result;
    }

    public DockingStation getStationById(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        DockingStation result = response.readEntity(DockingStation.class);
        System.out.println(result);
        return result;
    }

    public DockingStation updateStation(DockingStation station) {
        String id = Optional.ofNullable(station.getId()).orElse("");
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(station, MediaType.APPLICATION_JSON));
        return response.getStatus() == 200 ? response.readEntity(DockingStation.class) : null;
    }

    public DockingStation addStation(DockingStation station) {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(station, MediaType.APPLICATION_JSON));
        return response.getStatus() == 200 ? response.readEntity(DockingStation.class) : null;
    }

    public boolean deleteStation(String id) {
        WebTarget webTarget = client.target(PATH).path(id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();
        return response.getStatus() == 200;
    }
}
