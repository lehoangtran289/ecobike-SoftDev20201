package com.hust.ebr.serverapi;

import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.CreditCardReqDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class RentingApi {
    public static final String PATH = "http://localhost:8080/api/docking-stations";

    private Client client;

    public RentingApi() {
        client = ClientBuilder.newClient();
    }

    public CreditCard getCreditCardInfo(String cardNumber) {
        return null;
    }

    public CreditCard creditCardAction(String cardNumber, CreditCardReqDTO dto) {
        return null;
    }
}
