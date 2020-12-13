package com.hust.ebr.controller;

import com.hust.ebr.model.Bike;
import com.hust.ebr.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BikeController {
    private final BikeService bikeService;

    @GetMapping("/bikes")
    public List<Bike> getBikes() {
        return bikeService.getBikes(null);
    }

    @GetMapping("/bikes/{id}")
    public Bike getBike(@PathVariable("id") String id) {
        return Optional.ofNullable(id).map(bikeService::findById).orElse(null);
    }

    @PostMapping("/bikes")
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.save(bike);
    }

    @DeleteMapping("/bikes/{id}")
    public ResponseEntity<?> deleteBike(@PathVariable("id") String id) {
        if (bikeService.findById(id) != null) {
            bikeService.deleteBike(id);
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.badRequest().body("invalid id");
    }

}
