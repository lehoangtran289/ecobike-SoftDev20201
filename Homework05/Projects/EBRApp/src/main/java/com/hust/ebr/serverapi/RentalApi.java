package com.hust.ebr.serverapi;

import com.hust.ebr.beans.DockingStation;
import com.hust.ebr.beans.Rental;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

public class RentalApi {
    public static final String PATH = "http://localhost:8080/api/rentals";

    private final Client client;

    public RentalApi() {
        client = ClientBuilder.newClient();
    }

    public List<Rental> getRentals(Map<String, String> params) {
        WebTarget webTarget = client.target(PATH);
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet())
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
        }
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<Rental> result = response.readEntity(new GenericType<List<Rental>>() {
        });
        System.out.println(result);
        return result;
    }

    public List<Rental> getRentalsByCardNumber(String cardNumber) {
        WebTarget webTarget = client.target(PATH).path(cardNumber);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        List<Rental> result = response.readEntity(new GenericType<List<Rental>>() {
        });
        System.out.println(result);
        return result;
    }

    public Rental saveNewRental(Rental rental) {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(rental, MediaType.APPLICATION_JSON));
        return response.getStatus() == 200 ? response.readEntity(Rental.class) : null;
    }

    public static void main(String[] args) {
        System.out.println(new RentalApi().getRentals(null));
    }
}
