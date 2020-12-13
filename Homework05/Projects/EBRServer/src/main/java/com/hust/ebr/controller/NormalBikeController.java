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

    @PutMapping("/normalBikes/{id}")
    public ResponseEntity<?> updateNormalBikes(@PathVariable("id") String id,
                                                  @RequestBody NormalBike normalBike) {
        if (!normalBike.getId().equals(id) || !bikeService.getNormalBikeIds().contains(id))
            return ResponseEntity.badRequest().body("invalid id");
        return ResponseEntity.ok(bikeService.update(normalBike));
    }

    @DeleteMapping("/normalBikes/{id}")
    public ResponseEntity<?> deleteNormalBikes(@PathVariable("id") String id) {
        if (bikeService.getNormalBikeIds().contains(id)) {
            bikeService.deleteBike(id);
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.badRequest().body("invalid NormalBike id!");
    }
}
