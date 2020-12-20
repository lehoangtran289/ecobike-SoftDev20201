package com.hust.ebr.controller;

import com.hust.ebr.model.Bike;
import com.hust.ebr.model.TwinBike;
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
public class TwinBikeController {
    private final BikeService bikeService;

    @GetMapping("/twinBikes")
    public List<Bike> getTwinBikes(TwinBike twinBike) {
        return bikeService.getBikes(twinBike);
    }
}
