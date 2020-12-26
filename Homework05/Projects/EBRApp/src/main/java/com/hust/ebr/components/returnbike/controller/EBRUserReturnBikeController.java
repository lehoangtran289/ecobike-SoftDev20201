package com.hust.ebr.components.returnbike.controller;

import com.hust.ebr.beans.*;
import com.hust.ebr.beans.DTO.RequestType;
import com.hust.ebr.serverapi.BankingApi;
import com.hust.ebr.serverapi.BikeApi;
import com.hust.ebr.serverapi.RentalApi;
import com.hust.ebr.utils.CostCalculator;

import javax.swing.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class EBRUserReturnBikeController {
    private CreditCard creditCard;
    private Bike bike;
    private Rental rental;
    private ZonedDateTime timeReturn;

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public ZonedDateTime getTimeReturn() {
        return timeReturn;
    }

    public ZonedDateTime getTimeBegin() {
        return timeBegin;
    }

    public String getCurrentStationId() {
        return currentStationId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    private ZonedDateTime timeBegin;
    private String currentStationId;
    private double totalCost;
    private double depositCost;
    public EBRUserReturnBikeController(Bike bike, CreditCard creditCard, ZonedDateTime timeBegin){
        this.bike = bike;
        this.depositCost = bike.getCost();
        this.creditCard = creditCard;
        this.timeReturn = ZonedDateTime.now();
        this.timeBegin = timeBegin;
        this.currentStationId = bike.getDockingStationId();
        totalCost = CostCalculator.calculateRentalFee(bike, timeBegin, timeReturn);
    }

    public void handleButtonEvent(JDialog rootDialog, JComboBox stationIdSelectionBox, JButton PAYANDRETURNButton, JLabel labelToDocking) {
        stationIdSelectionBox.addActionListener(e -> {
            currentStationId = (String) stationIdSelectionBox.getSelectedItem();
            labelToDocking.setText("To Docking: " + currentStationId);
        });
        PAYANDRETURNButton.addActionListener(e -> {
            // TODO
            BankingApi bankingApi = new BankingApi();
            creditCard = bankingApi.requestCreditCard(RequestType.Refund, creditCard.getCardNumber(),
                    depositCost);
            creditCard = bankingApi.requestCreditCard(RequestType.Deduct, creditCard.getCardNumber(),
                    totalCost);
            creditCard = bankingApi.updateCreditCard(creditCard.getCardNumber(), false);

            bike.setStatus(Bike.Status.Available);
            bike.setDockingStationId(currentStationId);
            bike = new BikeApi().updateBike(bike);

            rental = new Rental();
            rental.setBikeId(bike.getId());
            rental.setRentalDate(Date.from(timeBegin.toInstant()));
            if (bike instanceof NormalBike)
                rental.setBikeType(Rental.Type.NormalBike);
            else if (bike instanceof EBike)
                rental.setBikeType(Rental.Type.EBike);
            else
                rental.setBikeType(Rental.Type.TwinBike);
            rental.setCardNumber(creditCard.getCardNumber());
            rental.setCardOwner(creditCard.getCardOwner());
            rental.setFromStationId(bike.getDockingStationId());
            rental.setToStationId(currentStationId);
            rental.setTotalMoney(totalCost);
            rental.setTotalTime(ChronoUnit.SECONDS.between(timeBegin, timeReturn));
            rental = new RentalApi().saveNewRental(rental);
            rootDialog.dispose();
        });
    }

}
