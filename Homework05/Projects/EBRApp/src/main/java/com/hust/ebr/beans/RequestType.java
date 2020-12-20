package com.hust.ebr.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RequestType {
    @JsonProperty("Deduct") Deduct,
    @JsonProperty("Refund") Refund
}
