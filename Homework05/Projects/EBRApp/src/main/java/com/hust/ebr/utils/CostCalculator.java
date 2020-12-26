package com.hust.ebr.utils;

import com.hust.ebr.beans.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.hust.ebr.utils.Constants.*;

public class CostCalculator {
    public CostCalculator() {

    }

    public static long calculateRentalFee(Bike bike, ZonedDateTime timeRent, ZonedDateTime timeReturn) {
        long totalCost = 0;
        double diff = ChronoUnit.MINUTES.between(timeRent, timeReturn);
        double v = FIRST_COST_TIME_COST + Math.ceil((diff - FIRST_COST_TIME_AMOUNT) / NEXT_COST_TIME_AMOUNT) * COST_EVERY_NEXT_MINUTES;
        if (FREE_TIME_AMOUNT <= diff && diff <= FIRST_COST_TIME_AMOUNT) {
            if (bike instanceof NormalBike)
                totalCost = FIRST_COST_TIME_COST;
            else if(bike instanceof TwinBike)
                totalCost = (long) (FIRST_COST_TIME_COST * TWINBIKE_MULTIPLIER);
            else if(bike instanceof EBike)
                totalCost = (long) (FIRST_COST_TIME_COST * EBIKE_MULTIPLIER);
        } else if (diff > FIRST_COST_TIME_AMOUNT) {
            if (bike instanceof NormalBike)
                totalCost = (long) v;
            else if(bike instanceof TwinBike)
                totalCost = (long) (v * TWINBIKE_MULTIPLIER);
            else if(bike instanceof EBike)
                totalCost = (long) (v * EBIKE_MULTIPLIER);
        }
//        System.out.println(diff);
        return totalCost;
    }

    public static double maxTimeRenting(Bike bike, CreditCard creditCard) {
        double maxTime = 0;
        double balance = creditCard.getBalance();
        double depositCost = bike.getCost();
        if (bike instanceof NormalBike) {
            if ((balance - depositCost) < FIRST_COST_TIME_COST) {
                maxTime = FIRST_COST_TIME_AMOUNT;
            } else {
                maxTime = FIRST_COST_TIME_AMOUNT + ((balance - depositCost - FIRST_COST_TIME_COST) / COST_EVERY_NEXT_MINUTES) * NEXT_COST_TIME_AMOUNT;
            }
        } else if (bike instanceof TwinBike) {
            if ((balance - depositCost) < FIRST_COST_TIME_COST*TWINBIKE_MULTIPLIER) {
                maxTime = FIRST_COST_TIME_AMOUNT;
            } else {
                maxTime = FIRST_COST_TIME_AMOUNT + ((balance - depositCost - FIRST_COST_TIME_COST*1.5) / COST_EVERY_NEXT_MINUTES*TWINBIKE_MULTIPLIER) * NEXT_COST_TIME_AMOUNT;

            }
        } else if (bike instanceof EBike){
            if ((balance - depositCost) < FIRST_COST_TIME_COST*EBIKE_MULTIPLIER) {
                maxTime = FIRST_COST_TIME_AMOUNT;
            } else {
                maxTime = FIRST_COST_TIME_AMOUNT + ((balance - depositCost - FIRST_COST_TIME_COST*1.5) / COST_EVERY_NEXT_MINUTES*EBIKE_MULTIPLIER) * NEXT_COST_TIME_AMOUNT;

            }
        }
        return maxTime;
    }

    public static long rentingCost24h(ZonedDateTime timeRent, ZonedDateTime timeReturn){
        long totalCost;
        double diff = ChronoUnit.MINUTES.between(timeRent, timeReturn);
        if (diff/60 <= REFUND_TIME)
            totalCost = (long) (COST_FOR_TIME_AMOUNT - (REFUND_TIME- diff/60)*REFUND_EACH_HOUR_EARLY);
        else if((diff/60>REFUND_TIME) && (diff/60<=TIME_AMOUNT))
            totalCost = COST_FOR_TIME_AMOUNT;
        else
            totalCost = (long) (COST_FOR_TIME_AMOUNT + ((diff - TIME_AMOUNT*60)/NEW_NEXT_TIME_AMOUNT)*NEW_NEXT_COST_TIME_AMOUNT);
        return totalCost;
    }
}
