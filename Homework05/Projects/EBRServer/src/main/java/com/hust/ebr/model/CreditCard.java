package com.hust.ebr.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class CreditCard {
    @NotBlank
    @Size(min = 9, max = 16)
    private String cardNumber;

    @NotBlank
    private String cardOwner;

    @PositiveOrZero
    private double balance;

    public boolean match(CreditCard creditCard) {
        if (creditCard == null)
            return true;

        if (StringUtils.hasText(creditCard.cardNumber) && !this.cardNumber.equals(creditCard.cardNumber)) {
            return false;
        }
        if (StringUtils.hasText(creditCard.cardOwner) && !this.cardOwner.equals(creditCard.cardOwner)) {
            return false;
        }
        if (creditCard.balance >= 0 && this.balance != creditCard.balance) {
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
