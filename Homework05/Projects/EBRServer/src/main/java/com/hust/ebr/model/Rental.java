package com.hust.ebr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private Type bikeType;
    private String bikeId;
    private String cardNumber;
    private String cardOwner;
    private String fromStationId;
    private String toStationId;
    private Date rentalDate;
    private long totalTime;
    private double totalMoney;

    public boolean match(Rental rental) {
        if (rental == null)
            return true;
        if (rental.bikeType != null && rental.bikeType != this.bikeType) {
            return false;
        }
        if (StringUtils.hasText(rental.bikeId) && !this.bikeId.contains(rental.bikeId)) {
            return false;
        }
        if (StringUtils.hasText(rental.cardNumber) && !this.cardNumber.contains(rental.cardNumber)) {
            return false;
        }
        if (StringUtils.hasText(rental.cardOwner) && !this.cardOwner.contains(rental.cardOwner)) {
            return false;
        }
        if (StringUtils.hasText(rental.fromStationId) && !this.fromStationId.contains(rental.fromStationId)) {
            return false;
        }
        if (StringUtils.hasText(rental.toStationId) && !this.toStationId.contains(rental.toStationId)) {
            return false;
        }
        if (rental.rentalDate != null && !this.rentalDate.equals(rental.rentalDate)) {
            return false;
        }
        if (rental.totalTime > 0 && this.totalTime != rental.totalTime) {
            return false;
        }
        if (rental.totalMoney > 0 && this.totalMoney != rental.totalMoney) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rental) {
            return this.cardNumber.equals(((Rental) obj).cardNumber);
        }
        return false;
    }

    public enum Type {NormalBike, EBike, TwinBike}
}
