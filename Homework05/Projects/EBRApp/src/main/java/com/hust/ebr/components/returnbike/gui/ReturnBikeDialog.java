package com.hust.ebr.components.returnbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.abstractdata.gui.ADataCreditCardDialog;

public class ReturnBikeDialog extends ADataCreditCardDialog<Bike> {
    public ReturnBikeDialog(Bike bike){
        super(bike);
    }

    @Override
    public void checkCreditCard(String cardNumber) {

    }

}
