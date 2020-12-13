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

    @PutMapping("/twinBikes/{id}")
    public ResponseEntity<?> updateTwinBike(@PathVariable("id") String id, @RequestBody TwinBike twinBike) {
        if (!twinBike.getId().equals(id) || !bikeService.getTwinBikeIds().contains(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
        return ResponseEntity.ok(bikeService.update(twinBike));
    }

    @DeleteMapping("/twinBikes/{id}")
    public ResponseEntity<?> deleteTwinBike(@PathVariable("id") String id) {
        if (bikeService.getTwinBikeIds().contains(id)) {
            bikeService.deleteBike(id);
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.badRequest().body("invalid TwinBike id!");
    }
}
