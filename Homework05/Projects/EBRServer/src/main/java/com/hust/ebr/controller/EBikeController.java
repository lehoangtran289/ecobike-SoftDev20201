package com.hust.ebr.controller;

import com.hust.ebr.model.Bike;
import com.hust.ebr.model.EBike;
import com.hust.ebr.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
public class EBikeController {

    private final BikeService bikeService;

    @GetMapping("/eBikes")
    public List<Bike> getEBikes(EBike eBike) {
        return bikeService.getBikes(eBike);
    }
}
