package com.hust.ebr.serverapi.abstractdata;

import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.beans.DTO.RequestType;

public interface IBankingApi extends IServerApi{
    String PATH = "http://localhost:8080/api/credit-cards";

    CreditCard getCreditCardInfo(String cardNumber);

    CreditCard requestCreditCard(RequestType type, String cardNumber, double amount);

    CreditCard updateCreditCard(String cardNumber, Boolean isRenting);
}
