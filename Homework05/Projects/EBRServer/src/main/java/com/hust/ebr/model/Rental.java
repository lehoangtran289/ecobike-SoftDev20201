package com.hust.ebr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private String bikeId;
    private String cardNumber;
    private String cardOwner;
    private String fromStationId;
    private String toStationId;
    private Date rentalDate;
    private long totalTime;
    private double totalMoney;
}
