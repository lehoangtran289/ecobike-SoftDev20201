package com.hust.ebr.beans;

import lombok.Data;

@Data
public class Motor {
    private String id;
    private int batteryPercentage;
    private int loadCycles;
    private long remainingTime;
}