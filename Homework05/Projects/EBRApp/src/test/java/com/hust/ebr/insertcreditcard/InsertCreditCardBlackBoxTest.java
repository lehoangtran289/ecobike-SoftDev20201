package com.hust.ebr.insertcreditcard;

import com.hust.ebr.beans.NormalBike;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertCreditCardBlackBoxTest {
    
    private EBRUserRentBikeController controller = new EBRUserRentBikeController(BankingApi.singleton(), BikeApi.singleton());

    /*@BeforeClass
    public void setUp() throws Exception {
        controller = new EBRUserRentBikeController(BankingApi.singleton(), BikeApi.singleton());
    }*/
    
    @Test
    public void testInvalidCardNumber() {
        int expected = EBRUserRentBikeController.INVALID_CARD;
        int actual = controller.checkCreditCard("abc", new NormalBike());
        assertEquals(expected, actual);
    }

    @Test
    public void testUnavailableCard() {
        int expected = EBRUserRentBikeController.CARD_UNAVAILABLE;
        int actual = controller.checkCreditCard("201767642017", new NormalBike());
        //actual = EBRUserRentBikeController.CARD_UNAVAILABLE;
        assertEquals(expected, actual);
    }

    @Test
    public void testTransactionFailed() {
        int expected = EBRUserRentBikeController.NOT_ENOUGH_MONEY;
        int actual = controller.checkCreditCard("201767962017", new NormalBike());
        assertEquals(expected, actual);
    }

    @Test
    public void testValidCard() {
        int expected = EBRUserRentBikeController.CARD_ACCEPTED;
        int actual = controller.checkCreditCard("201768612017", new NormalBike());
        assertEquals(expected, actual);
    }
}
