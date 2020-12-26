package com.hust.ebr.serverapi.abstractdata;

import com.hust.ebr.beans.Rental;

import java.util.List;
import java.util.Map;

public interface IRentalApi extends IServerApi {
    String PATH = "http://localhost:8080/api/rentals";

    List<Rental> getRentals(Map<String, String> params);
    List<Rental> getRentalsByCardNumber(String cardNumber);
    Rental saveNewRental(Rental rental);
}
