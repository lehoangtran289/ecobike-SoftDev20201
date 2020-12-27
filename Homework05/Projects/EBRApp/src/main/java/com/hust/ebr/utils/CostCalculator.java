package com.hust.ebr.utils;

import com.hust.ebr.beans.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.hust.ebr.utils.Constants.*;

public class CostCalculator {
    public static long calculateRentalFee(Bike bike, ZonedDateTime timeRent, ZonedDateTime timeReturn) {
        long totalCost = 0;
        double diff = ChronoUnit.MINUTES.between(timeRent, timeReturn);
        double v =
                FIRST_COST_TIME_COST + Math.ceil((diff - FIRST_COST_TIME_AMOUNT) / NEXT_COST_TIME_AMOUNT) * COST_EVERY_NEXT_MINUTES;
        if (FREE_TIME_AMOUNT <= diff && diff <= FIRST_COST_TIME_AMOUNT) {
            totalCost = (long) (FIRST_COST_TIME_COST * getMultiplier(bike));
        } else if (diff > FIRST_COST_TIME_AMOUNT) {
            totalCost = (long) (v * getMultiplier(bike));
        }
//        System.out.println(diff);
        return totalCost;
    }

    public static double maxTimeRenting(Bike bike, CreditCard creditCard) {
        double maxTime = 0;
        double balance = creditCard.getBalance();
        double depositCost = bike.getCost();
        if ((balance - depositCost) < FIRST_COST_TIME_COST * getMultiplier(bike)) {
            maxTime = FIRST_COST_TIME_AMOUNT;
        } else {
            maxTime =
                    FIRST_COST_TIME_AMOUNT + ((balance - depositCost - FIRST_COST_TIME_COST * getMultiplier(bike)) / COST_EVERY_NEXT_MINUTES * getMultiplier(bike)) * NEXT_COST_TIME_AMOUNT;

        }
        return maxTime;
    }

    public static long rentingCost24h(ZonedDateTime timeRent, ZonedDateTime timeReturn) {
        long totalCost;
        double diff = ChronoUnit.SECONDS.between(timeRent, timeReturn);
        if (diff / 60 <= REFUND_TIME)
            totalCost = (long) (COST_FOR_TIME_AMOUNT - (REFUND_TIME - diff / 60) * REFUND_EACH_HOUR_EARLY);
        else if ((diff / 60 > REFUND_TIME) && (diff / 60 <= TIME_AMOUNT))
            totalCost = COST_FOR_TIME_AMOUNT;
        else
            totalCost =
                    (long) (COST_FOR_TIME_AMOUNT + ((diff - TIME_AMOUNT * 60) / NEW_NEXT_TIME_AMOUNT) * NEW_NEXT_COST_TIME_AMOUNT);
        return totalCost;
    }

    private static double getMultiplier(Bike bike) {
        if (bike instanceof NormalBike)
            return 1;
        else if (bike instanceof TwinBike)
            return TWINBIKE_MULTIPLIER;
        else if (bike instanceof EBike)
            return EBIKE_MULTIPLIER;
        return 1;
    }
}
