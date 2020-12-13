package com.hust.ebr.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
public class EBike extends Bike {
    private static final int SADDLE_COUNT = 1;
    private static final int PEDAL_COUNT = 1;
    private static final int SEAT_COUNT = 1;

    private Motor motor;

    @Override
    public boolean match(Bike bike) {
        if (bike == null)
            return true;
        boolean res = super.match(bike);
        if (!res)
            return false;
        if (!(bike instanceof EBike))
            return false;
        EBike eBike = (EBike) bike;
        if (eBike.motor != null) {
            if (eBike.motor.getId() != null && !eBike.motor.getId().equals("") && this.motor.getId().contains(eBike.motor.getId())) {
                return false;
            }
            if (eBike.motor.getBatteryPercentage() != 0 && this.motor.getBatteryPercentage() != eBike.motor.getBatteryPercentage()) {
                return false;
            }
            if (eBike.motor.getLoadCycles() != 0 && this.motor.getLoadCycles() != eBike.motor.getLoadCycles()) {
                return false;
            }
            if (eBike.motor.getRemainingTime() != 0 && this.motor.getRemainingTime() != eBike.motor.getRemainingTime()) {
                return false;
            }
        }
        return true;
    }
}
