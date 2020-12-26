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

    public RentBikeCreditCardDialog(EBRUserRentBikeController controller, Bike bike) {
        super(controller, bike);
        setModal(true);
    }

    @Override
    public void performNextState(EBRUserRentBikeController controller) {
        this.dispose();
        controller.getUserRentBikePage(t, cardNumber);
    }
}
