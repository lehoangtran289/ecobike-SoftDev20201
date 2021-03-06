package com.hust.ebr.model.dto.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.PositiveOrZero;

@Data
public class CreditCardReqDTO {
    @NonNull
    private RequestType type;

    @NonNull
    @PositiveOrZero
    private double amount;

    public CreditCardReqDTO() {
    }
}
