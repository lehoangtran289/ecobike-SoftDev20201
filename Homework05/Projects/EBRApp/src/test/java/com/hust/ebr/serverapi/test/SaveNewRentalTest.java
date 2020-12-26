package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.Rental;
import com.hust.ebr.serverapi.RentalApi;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SaveNewRentalTest {
    RentalApi rentalApi = new RentalApi();

    @Test
    public void testSaveNewRental() {
        Rental rental = new Rental();
        rental.setCardOwner("hoangtl3");
        rental.setCardNumber("201702242017");

        rentalApi.saveNewRental(rental);
        List<Rental> rentalList = rentalApi.getRentals(null);
        assertTrue("Error in saveNewRental API", rentalList.contains(rental));
    }
}
