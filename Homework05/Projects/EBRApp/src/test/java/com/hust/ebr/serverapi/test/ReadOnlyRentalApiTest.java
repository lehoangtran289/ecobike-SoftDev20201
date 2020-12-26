package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.serverapi.RentalApi;
import com.hust.ebr.serverapi.abstractdata.IRentalApi;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReadOnlyRentalApiTest {
    private final IRentalApi rentalApi = RentalApi.singleton();

    @Test
    public void testGetRentals() {
        List<Rental> rentalList = rentalApi.getRentals(null);
        assertEquals("Error in getRentals API", 3,  rentalList.size());

        Map<String, String> params = new HashMap<>();
        String cardOwner = "hoangtl1";
        params.put("cardOwner", cardOwner);
        rentalList = rentalApi.getRentals(params);
        assertEquals("Error in getRentals API", cardOwner, rentalList.get(0).getCardOwner());

        String bikeId = "eb2";
        params.put("bikeId", bikeId);
        rentalList = rentalApi.getRentals(params);
        assertEquals("Error in getRentals API", bikeId, rentalList.get(0).getBikeId());

        params.replace("bikeId", "wrongId");
        rentalList = rentalApi.getRentals(params);
        assertEquals("Error in getRentals API", 0, rentalList.size());
    }

    @Test
    public void testGetRentalsByCardNumber() {
        String cardNumber = "123456789";
        List<Rental> rentalList = rentalApi.getRentalsByCardNumber(cardNumber);
        assertTrue("Error in getRentalByCardNumber API", rentalList.isEmpty());

        cardNumber = "201702242017";
        rentalList = rentalApi.getRentalsByCardNumber(cardNumber);
        assertEquals("Error in getRentalByCardNumber API", cardNumber,rentalList.get(0).getCardNumber());
    }
}
