package com.hust.ebr.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class CreditCard {

    private String cardNumber;
    private String cardOwner;

    @PositiveOrZero
    private double balance;

    @JsonProperty("isRentingBike")
    private Boolean isRentingBike;

    public boolean match(CreditCard creditCard) {
        if (creditCard == null)
            return true;

        if (creditCard.cardNumber != null && !creditCard.cardNumber.equals("") && !this.cardNumber.equals(creditCard.cardNumber)) {
            return false;
        }
        if (creditCard.cardOwner != null && !creditCard.cardOwner.equals("") && !this.cardOwner.equals(creditCard.cardOwner)) {
            return false;
        }
        if (creditCard.balance >= 0 && this.balance != creditCard.balance) {
            return false;
        }
        if (creditCard.isRentingBike != null && this.isRentingBike != creditCard.isRentingBike) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CreditCard) {
            return this.cardNumber.equals(((CreditCard) obj).cardNumber);
        }
        return false;
    }
}
