package com.hust.ebr.controller;

import com.hust.ebr.model.Bike;
import com.hust.ebr.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BikeController {
    private final BikeService bikeService;

    @GetMapping("/bikes")
    public List<Bike> getBikes(String type, Bike bike) {
        return StringUtils.hasText(type) ? bikeService.getBikes(type, bike) : bikeService.getBikes(bike);
    }

    @GetMapping("/bikes/{id}")
    public Bike getBike(@PathVariable("id") String id) {
        return StringUtils.hasText(id) ? bikeService.findById(id) : null;
    }

    @PostMapping("/bikes")
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.save(bike);
    }

    @PutMapping("/bikes/{id}")
    public ResponseEntity<?> updateBike(@PathVariable("id") String id, @RequestBody Bike bike) {
        if (!StringUtils.hasText(bike.getId()) || !bikeService.getBikeIds().contains(id))
            return ResponseEntity.badRequest().body("");
        return ResponseEntity.ok(bikeService.update(bike));
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
