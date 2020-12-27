package com.hust.ebr.returnbike;

import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.NormalBike;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;
import com.hust.ebr.components.returnbike.controller.EBRUserReturnBikeController;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.RentalApi;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class ReturnBikeWhiteBoxTest {

    private EBRUserReturnBikeController controller = new EBRUserReturnBikeController(new NormalBike(), new CreditCard(), ZonedDateTime.now(),
            BankingApi.singleton(), BikeApi.singleton(), RentalApi.singleton());

    @Test
    public void testReturnBikeSuccess() {
        int expected = EBRUserReturnBikeController.STATION_AVAILABLE;
        int actual = controller.checkDockingStation("ds2");
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnBikeFailed1() {
        int expected = EBRUserReturnBikeController.STATION_INVALID;
        int actual = controller.checkDockingStation("123abcd");
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnBikeFailed2() {
        int expected = EBRUserReturnBikeController.STATION_FULL;
        int actual = controller.checkDockingStation("ds4");
        assertEquals(expected, actual);
    }

}
