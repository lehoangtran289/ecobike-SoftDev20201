package com.hust.ebr.serverapi;

import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.DTO.CreditCardReqDTO;
import com.hust.ebr.beans.DTO.RequestType;
//import sun.rmi.runtime.Log;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BankingApi {
    public static final String PATH = "http://localhost:8080/api/credit-cards";

    private final Client client;

    public BankingApi() {
        client = ClientBuilder.newClient();
    }

    public CreditCard getCreditCardInfo(String cardNumber) {
        WebTarget webTarget = client.target(PATH).path(cardNumber);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        CreditCard result = response.readEntity(CreditCard.class);
        System.out.println(result);
        System.out.println(response);
        return result;
    }

    /**
     * action to refund deposit or deduct money from credit card
     *
     * @param type       (RequestType.Deduct or RequestType.Refund)
     * @param cardNumber
     * @param amount
     * @return CreditCard if success, else null (if cardNumber not available or amount is not enough to deduct)
     */
    public CreditCard requestCreditCard(RequestType type, String cardNumber, double amount) {
        CreditCardReqDTO reqDTO = new CreditCardReqDTO(type, amount);
        WebTarget webTarget = client.target(PATH).path(cardNumber);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(reqDTO, MediaType.APPLICATION_JSON));
        CreditCard result = response.readEntity(CreditCard.class);
        System.out.println(result);
        System.out.println(response);
        return result;
    }
}
