package com.hust.ebr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;

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

        if (StringUtils.hasText(creditCard.cardNumber) && !this.cardNumber.equals(creditCard.cardNumber)) {
            return false;
        }
        if (StringUtils.hasText(creditCard.cardOwner) && !this.cardOwner.equals(creditCard.cardOwner)) {
            return false;
        }
        if (creditCard.balance > 0 && this.balance != creditCard.balance) {
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
