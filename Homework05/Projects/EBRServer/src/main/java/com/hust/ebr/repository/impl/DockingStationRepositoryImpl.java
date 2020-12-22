package com.hust.ebr.repository.impl;

import com.hust.ebr.model.Bike;
import com.hust.ebr.model.DockingStation;
import com.hust.ebr.repository.BikeRepository;
import com.hust.ebr.repository.DockingStationRepository;
import com.hust.ebr.repository.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DockingStationRepositoryImpl implements DockingStationRepository {

    private final Seed seed;
    private List<DockingStation> dockingStations;
    private final BikeRepository bikeRepository;

    public DockingStationRepositoryImpl(Seed seed, @Lazy BikeRepository bikeRepository) {
        this.seed = seed;
        this.bikeRepository = bikeRepository;
    }

    @PostConstruct
    public void init() {
        dockingStations = seed.getDockingStations();
    }

    @Override
    public List<DockingStation> search(DockingStation dockingStation) {
        return dockingStations.stream()
                .filter(station -> station.match(dockingStation))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DockingStation> findById(String id) {
        return dockingStations.stream()
                .filter(dockingStation -> dockingStation.getId().equals(id))
                .findAny();
    }

    @Override
    public DockingStation update(DockingStation dockingStation) {
        dockingStations = dockingStations.stream()
                .map(ds -> ds.equals(dockingStation) ? dockingStation : ds)
                .collect(Collectors.toList());
        return findById(dockingStation.getId()).orElse(null);
    }

    @Override
    public DockingStation save(DockingStation dockingStation) {
        if (findById(dockingStation.getId()).isPresent())
            return null;
        else {
            dockingStations.add(dockingStation);
            return dockingStation;
        }
    }

    @Override
    public void delete(String id) {
        findById(id).ifPresent(dockingStation -> {
            updateBikeAfterRemovingStation(dockingStation);
            dockingStations = dockingStations.stream()
                    .filter(ds -> !ds.getId().equals(id))
                    .collect(Collectors.toList());
        });

    }

    private void updateBikeAfterRemovingStation(DockingStation dockingStation) {
        List<Bike> bikes = dockingStation.getBikeIds()
                .stream()
                .map(id -> bikeRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        bikes.forEach(b -> {
            b.setDockingStationId(null);
            bikeRepository.update(b);
        });
    }
}
