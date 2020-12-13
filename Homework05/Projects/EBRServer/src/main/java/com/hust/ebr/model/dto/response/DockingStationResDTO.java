package com.hust.ebr.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DockingStationResDTO {
    private String id;
    private String stationName;
    private String stationAddress;
    private int emptyDockCount;
    private int normalBikeCount;
    private int twinBikeCount;
    private int eBikeCount;
    private List<String> bikeIds;
}
