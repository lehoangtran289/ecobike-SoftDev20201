package com.hust.ebr.controller;

import com.hust.ebr.model.Bike;
import com.hust.ebr.model.NormalBike;
import com.hust.ebr.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
public class NormalBikeController {
    private final BikeService bikeService;

    @GetMapping("/normalBikes")
    public List<Bike> getNormalBikes(NormalBike normalBike) {
        return bikeService.getBikes(normalBike);
    }
}
