package com.hust.ebr.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date rentalDate;
    private long totalTime;
    private double totalMoney;

    public boolean match(Rental rental) {
        if (rental == null)
            return true;
        if (rental.bikeType != null && rental.bikeType != this.bikeType) {
            return false;
        }
        if (rental.cardNumber != null && !rental.cardNumber.equals("") && !this.cardNumber.contains(rental.cardNumber)) {
            return false;
        }
        if (rental.cardOwner != null && !rental.cardOwner.equals("") && !this.cardOwner.contains(rental.cardOwner)) {
            return false;
        }
        if (rental.fromStationId != null && !rental.fromStationId.equals("") && !this.fromStationId.contains(rental.fromStationId)) {
            return false;
        }
        if (rental.toStationId != null && !rental.toStationId.equals("") && !this.toStationId.contains(rental.toStationId)) {
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
