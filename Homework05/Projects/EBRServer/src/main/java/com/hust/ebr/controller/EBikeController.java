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

    @PutMapping("/eBikes/{id}")
    public ResponseEntity<?> updateEBike(@PathVariable("id") String id, @RequestBody EBike ebike) {
        if (!ebike.getId().equals(id) || !bikeService.getEBikeIds().contains(id))
            return ResponseEntity.badRequest().body("invalid id");
        return ResponseEntity.ok(bikeService.update(ebike));
    }

    @DeleteMapping("/eBikes/{id}")
    public ResponseEntity<?> deleteEBike(@PathVariable("id") String id) {
        if (bikeService.getEBikeIds().contains(id)) {
            bikeService.deleteBike(id);
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.badRequest().body("invalid Ebike id!");
    }
}
