package com.hust.ebr.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
public class TwinBike extends Bike {
    private static final int SADDLE_COUNT = 2;
    private static final int PEDAL_COUNT = 2;
    private static final int SEAT_COUNT = 1;

    @Override
    public boolean match(Bike bike) {
        if (bike == null)
            return true;
        boolean res = super.match(bike);
        if (!res)
            return false;
        return true;
    }
}
