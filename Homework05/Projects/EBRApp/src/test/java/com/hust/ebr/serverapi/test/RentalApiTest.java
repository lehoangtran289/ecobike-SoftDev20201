package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.serverapi.RentalApi;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RentalApiTest {
    RentalApi rentalApi = new RentalApi();

    @Test
    public void testGetRentals() {
        List<Rental> rentalList = rentalApi.getRentals(null);
        assertEquals("Error in getRentals API", 3,  rentalList.size());
    }

    @Test
    public void testGetRentalsByCardNumber() {

    }

    @Test
    public void testSaveNewRental() {

    }
}
