package com.hust.ebr.serverapi.test;

import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.serverapi.BankingApi;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankingApiTest {
    BankingApi bankingApi = new BankingApi();

    @Test
    public void testGetCreditCardInfo() {
        String cardNumber = "123456789";
        CreditCard card = bankingApi.getCreditCardInfo(cardNumber);
        assertEquals("Error in getCreditCardInfo API", null, card);

        cardNumber = "201767642017";
        card = bankingApi.getCreditCardInfo(cardNumber);
        assertEquals("Error in getCreditCardInfo API", cardNumber, card.getCardNumber());
    }

    @Test
    public void testRequestCreditCard() {
        String cardNumber = "123456789";
        double amount = 50000;
        RequestType type = RequestType.Deduct;

        CreditCard card = bankingApi.requestCreditCard(type, cardNumber, amount);
        assertEquals("Error in getRequestCreditCard API", null, card);

        cardNumber = "201767642017";
        card = bankingApi.getCreditCardInfo(cardNumber);
        double currentBalance = card.getBalance();
        card = bankingApi.requestCreditCard(type, cardNumber, amount);
        assertEquals("Error in getRequestCreditCard API", currentBalance - amount, card.getBalance(), 0.0001);

        type = RequestType.Refund;
        card = bankingApi.requestCreditCard(type, cardNumber, amount);
        assertEquals("Error in getRequestCreditCard API", currentBalance, card.getBalance(), 0.0001);
    }
}
