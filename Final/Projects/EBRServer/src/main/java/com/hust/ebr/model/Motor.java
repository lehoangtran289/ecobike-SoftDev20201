package com.hust.ebr.model;

import lombok.Data;

@Data
public class Motor {
    private String id;
    private int batteryPercentage;
    private int loadCycles;
    private long remainingTime;
}
