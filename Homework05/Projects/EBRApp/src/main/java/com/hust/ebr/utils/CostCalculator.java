package com.hust.ebr.utils;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class costCalculator {
    public costCalculator(){

    }
    public long costCaculator(Bike bike, ZonedDateTime timeRent, ZonedDateTime timeReturn){
        long totalCost = 0;
        double diff = ChronoUnit.MINUTES.between(timeRent, timeReturn);
        double v = 10000 + Math.ceil((diff - 30) / 15.0) * 3000;
        if(diff <= 10){
            totalCost = 0;
        } else {
            if (diff <= 30){
                if(bike.getName() == "normalBike")
                    totalCost = 10000;
                else
                    totalCost = (long) (10000 * 1.5);
            } else if (diff > 30){
                if(bike.getName() == "normalBike"){
                    totalCost = (long) v;
                } else{
                    totalCost = (long) (v *1.5);
                }
            }
        }
//        System.out.println(diff);
        return totalCost;
    }

    public double maxTimeRenting(Bike bike, CreditCard creditCard){
        double maxTime;
        double balance = creditCard.getBalance();
        double depositCost = bike.getCost();
        if(bike.getName() == "normalBike"){
            if((balance-depositCost) < 10000){
                maxTime = 30;
            } else {
                maxTime = 30 + ((balance-depositCost-10000)/3000)*15;
            }
        } else {
            if((balance-depositCost) < 15000){
                maxTime = 30;
            } else {
                maxTime = 30 + ((balance-depositCost-15000)/4500)*15;

            }
        }
        return maxTime;
    }
}
