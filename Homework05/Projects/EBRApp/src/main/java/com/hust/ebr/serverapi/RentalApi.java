package com.hust.ebr.serverapi;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;
import com.hust.ebr.serverapi.abstractdata.IRentalApi;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

public class RentalApi implements IRentalApi {
    private static final IRentalApi singleton = new RentalApi();

    private RentalApi() {
    }

    public static IRentalApi singleton() {
        return singleton;
    }

    @Override
    public List<Rental> getRentals(Map<String, String> params) {
        WebTarget webTarget = client.target(PATH);
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet())
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
        }
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<Rental> result = response.readEntity(new GenericType<List<Rental>>() {
        });
        System.out.println("getRentals: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public List<Rental> getRentalsByCardNumber(String cardNumber) {
        WebTarget webTarget = client.target(PATH).path(cardNumber);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        List<Rental> result = response.readEntity(new GenericType<List<Rental>>() {
        });
        System.out.println("getRentalsByCardNumber: " + response);
        System.out.println(result);
        return result;
    }

    @Override
    public Rental saveNewRental(Rental rental) {
        WebTarget webTarget = client.target(PATH);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(rental, MediaType.APPLICATION_JSON));
        System.out.println("saveNewRental: " + response);
        return response.getStatus() == 200 ? response.readEntity(Rental.class) : null;
    }
}
