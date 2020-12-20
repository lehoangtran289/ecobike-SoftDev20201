package com.hust.ebr.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = NormalBike.class, name = "normalBike"),
        @Type(value = TwinBike.class, name = "twinBike"),
        @Type(value = EBike.class, name = "eBike")})
public class Bike {
    @NonNull
    private String id;
    private String name;
    private float weight;
    private String licensePlate;
    private Date manufacturingDate;
    private String producer;
    private float cost;
    private Status status;
    private String dockingStationId;

    public boolean match(Bike bike) {
        if (bike == null)
            return true;

        if (bike.id != null && !bike.id.equals("") && !this.id.contains(bike.id)) {
            return false;
        }
        if (bike.name != null && !bike.name.equals("") && !this.name.contains(bike.name)) {
            return false;
        }
        if (bike.weight != 0 && this.weight != bike.weight) {
            return false;
        }
        if (bike.licensePlate != null && !bike.licensePlate.equals("") && !this.licensePlate.contains(bike.licensePlate)) {
            return false;
        }
        if (bike.manufacturingDate != null && this.manufacturingDate.equals(bike.manufacturingDate)) {
            return false;
        }
        if (bike.producer != null && !bike.producer.equals("") && !this.producer.contains(bike.producer)) {
            return false;
        }
        if (bike.cost != 0 && this.cost != bike.cost) {
            return false;
        }
        if (StringUtils.hasText(bike.dockingStationId) && !this.dockingStationId.equals(bike.dockingStationId)) {
            return false;
        }
        if (bike.status != null && this.status != bike.status) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bike) {
            return this.id.equals(((Bike) obj).id);
        }
        return false;
    }

    public enum Status {Available, Renting}

}
