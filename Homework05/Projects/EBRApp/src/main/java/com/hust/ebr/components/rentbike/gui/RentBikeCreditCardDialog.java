package com.hust.ebr.components.rentbike.gui;

import com.hust.ebr.beans.*;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.components.abstractdata.gui.ADataCreditCardDialog;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.utils.Constants;

import javax.swing.*;

public class RentBikeCreditCardDialog extends ADataCreditCardDialog<Bike> {

    public RentBikeCreditCardDialog(Bike bike) {
        super(bike);
    }

    @Override
    public void checkCreditCard(String cardNumber) {
        BankingApi bankingApi = new BankingApi();
        CreditCard creditCard = bankingApi.getCreditCardInfo(cardNumber);
        //CreditCard creditCard = bankingApi.requestCreditCard(RequestType.Deduct, cardNumber, getBikeDeposit());
        if (creditCard == null) {
            JOptionPane.showMessageDialog(null,
                    "Credit card does not exist!",
                    "INVALID CREDIT CARD",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            if (creditCard.getIsRentingBike()) {
                JOptionPane.showMessageDialog(null,
                        "Someone is using this credit card!",
                        "CREDIT CARD NOT AVAILABLE",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                if (creditCard.getBalance() < getBikeDeposit()) {
                    JOptionPane.showMessageDialog(null,
                            "Your credit card does not have enough money to rent bike!",
                            "TRANSACTION FAILED",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    this.dispose();
                    creditCard = bankingApi.requestCreditCard(RequestType.Deduct, cardNumber, getBikeDeposit());
                    creditCard = bankingApi.updateCreditCard(creditCard.getCardNumber(), true);
                    t.setStatus(Bike.Status.Renting);
                    t = new BikeApi().updateBike(t);
                    new EBRUserRentBike(new EBRUserRentBikeController(), t, creditCard);
                }
            }
        }
    }

    private double getBikeDeposit() {
        if (t instanceof NormalBike) {
            return Constants.NORMAL_BIKE_DEPOSIT;
        } else if (t instanceof TwinBike) {
            return Constants.TWIN_BIKE_DEPOSIT;
        } else if (t instanceof EBike) {
            return Constants.EBIKE_DEPOSIT;
        } else {
            return 0;
        }
    }
}
