package com.hust.ebr.beans;

import lombok.Data;

import java.util.List;

@Data
public class DockingStation {
    private String id;
    private String stationName;
    private String stationAddress;
    private int totalDockCount;
    private int emptyDockCount;
    private int normalBikeCount;
    private int twinBikeCount;
    private int eBikeCount;
    private List<String> bikeIds;

    public boolean match(DockingStation station) {
        if (station == null)
            return true;

        if (station.id != null && !station.id.equals("") && !this.id.contains(station.id)) {
            return false;
        }
        if (station.stationName != null && !station.stationName.equals("") && !this.stationName.contains(station.stationName)) {
            return false;
        }
        if (station.stationAddress != null && !station.stationAddress.equals("") && !this.stationAddress.contains(station.stationAddress)) {
            return false;
        }
        if (station.emptyDockCount != 0 && this.emptyDockCount != station.emptyDockCount) {
            return false;
        }
        if (station.normalBikeCount != 0 && this.normalBikeCount != station.normalBikeCount) {
            return false;
        }
        if (station.twinBikeCount != 0 && this.twinBikeCount != station.twinBikeCount) {
            return false;
        }
        if (station.eBikeCount != 0 && this.eBikeCount != station.eBikeCount) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DockingStation) {
            return this.id.equals(((DockingStation) obj).id);
        }
        return false;
    }
}
