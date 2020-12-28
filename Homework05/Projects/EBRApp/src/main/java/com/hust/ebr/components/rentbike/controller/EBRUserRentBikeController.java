package com.hust.ebr.components.rentbike.controller;

import com.hust.ebr.beans.*;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.components.rentbike.gui.EBRUserRentBike;
import com.hust.ebr.serverapi.abstractdata.IBankingApi;
import com.hust.ebr.serverapi.abstractdata.IBikeApi;
import com.hust.ebr.utils.Constants;

import javax.swing.*;

public class EBRUserRentBikeController {

    public static final int INVALID_CARD = 1;
    public static final int CARD_UNAVAILABLE = 2;
    public static final int NOT_ENOUGH_MONEY = 3;
    public static final int CARD_ACCEPTED = 4;

    private final IBankingApi bankingApi;
    private final IBikeApi bikeApi;
    private CreditCard creditCard;

    public EBRUserRentBikeController(IBankingApi bankingApi, IBikeApi bikeApi) {
        this.bankingApi = bankingApi;
        this.bikeApi = bikeApi;
        creditCard = new CreditCard();
    }

    public int checkCreditCard(String cardNumber, Bike bike) {
        creditCard = bankingApi.getCreditCardInfo(cardNumber);
        if (creditCard == null) {
            JOptionPane.showMessageDialog(null,
                    "Credit card does not exist!",
                    "INVALID CREDIT CARD",
                    JOptionPane.PLAIN_MESSAGE);
            return INVALID_CARD;
        } else {
            if (creditCard.getIsRentingBike()) {
                JOptionPane.showMessageDialog(null,
                        "Someone is using this credit card!",
                        "CREDIT CARD NOT AVAILABLE",
                        JOptionPane.PLAIN_MESSAGE);
                return CARD_UNAVAILABLE;
            } else {
                if (creditCard.getBalance() < getBikeDeposit(bike)) {
                    JOptionPane.showMessageDialog(null,
                            "Your credit card does not have enough money to rent bike!",
                            "TRANSACTION FAILED",
                            JOptionPane.PLAIN_MESSAGE);
                    return NOT_ENOUGH_MONEY;
                } else {
                    return CARD_ACCEPTED;
                }
            }
        }
    }

    public double getBikeDeposit(Bike bike) {
        if (bike instanceof NormalBike) {
            return Constants.NORMAL_BIKE_DEPOSIT;
        } else if (bike instanceof TwinBike) {
            return Constants.TWIN_BIKE_DEPOSIT;
        } else if (bike instanceof EBike) {
            return Constants.EBIKE_DEPOSIT;
        } else {
            return 0;
        }
    }

    public void getUserRentBikePage(Bike bike, String cardNumber) {
        creditCard = bankingApi.requestCreditCard(RequestType.Deduct, cardNumber, getBikeDeposit(bike));
        creditCard = bankingApi.updateCreditCard(creditCard.getCardNumber(), true);
        bike.setStatus(com.hust.ebr.beans.Bike.Status.Renting);
        bike = bikeApi.updateBike(bike);
        new EBRUserRentBike(bike, creditCard);
    }

}
