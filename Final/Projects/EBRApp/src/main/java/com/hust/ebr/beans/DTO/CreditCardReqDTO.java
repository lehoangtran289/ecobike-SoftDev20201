package com.hust.ebr.beans.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
public class CreditCardReqDTO {
    @NonNull
    private RequestType type;

    @NonNull
    @PositiveOrZero
    private double amount;
}
