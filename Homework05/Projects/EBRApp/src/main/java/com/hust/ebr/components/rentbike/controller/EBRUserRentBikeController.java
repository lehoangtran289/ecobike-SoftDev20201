package com.hust.ebr.components.rentbike.controller;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.components.rentbike.gui.RentBikePage;

import javax.swing.*;

public class EBRUserRentBikeController {

    public EBRUserRentBikeController() {
    }

    public JPanel getRentBikePage(Bike bike, CreditCard creditCard) {
        return new RentBikePage(bike, creditCard);

    }
}
