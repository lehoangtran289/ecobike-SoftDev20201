package com.hust.ebr.controller;

import com.hust.ebr.model.DockingStation;
import com.hust.ebr.model.dto.response.DockingStationResDTO;
import com.hust.ebr.service.DockingStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DockingStationController {
    private final DockingStationService dockingStationService;

    @GetMapping("/docking-stations")
    public List<DockingStationResDTO> getDockingStations(DockingStation dockingStation) {
        return dockingStationService.getDockingStations(dockingStation);
    }

    @GetMapping("/docking-stations/{id}")
    public DockingStationResDTO getDockingStation(@PathVariable("id") String id) {
        return StringUtils.hasText(id) ? dockingStationService.findById(id) : null;
    }

    @GetMapping("/docking-stations/available")
    public List<DockingStationResDTO> getAvailableDockingStations() {
        return dockingStationService.getAvailableStations();
    }

    @PutMapping("/docking-stations/{id}")
    public ResponseEntity<?> updateDockingStation(@PathVariable("id") String id, @RequestBody DockingStation dockingStation) {
        if (!StringUtils.hasText(dockingStation.getId()) || !dockingStation.getId().equals(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        return ResponseEntity.ok(dockingStationService.update(dockingStation));
    }

    @PostMapping("/docking-stations")
    public DockingStationResDTO addDockingStation(@RequestBody DockingStation dockingStation) {
        return dockingStationService.save(dockingStation);
    }

    @DeleteMapping("/docking-stations/{id}")
    public ResponseEntity<?> deleteDockingStation(@PathVariable("id") String id) {
        if (dockingStationService.findById(id) != null) {
            dockingStationService.deleteDockingStation(id);
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().body("invalid id");
    }
}
