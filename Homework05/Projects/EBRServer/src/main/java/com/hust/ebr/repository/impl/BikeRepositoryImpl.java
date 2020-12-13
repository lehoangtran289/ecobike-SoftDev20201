package com.hust.ebr.repository.impl;

import com.hust.ebr.model.Bike;
import com.hust.ebr.repository.BikeRepository;
import com.hust.ebr.repository.DockingStationRepository;
import com.hust.ebr.repository.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BikeRepositoryImpl implements BikeRepository {

    private final Seed seed;
    private final DockingStationRepository dockingStationRepository;
    private List<Bike> bikes;

    @PostConstruct
    public void init() {
        bikes = seed.getBikes();
    }

    @Override
    public List<Bike> search(Bike bike) {
        return bikes.stream()
                .filter(b -> b.match(bike))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bike> findById(String id) {
        return bikes.stream()
                .filter(b -> b.getId().equals(id))
                .findAny();
    }

    @Override
    public Bike update(Bike bike) {
        bikes = bikes.stream().map(b -> b.equals(bike) ? bike : b).collect(Collectors.toList());
        return findById(bike.getId()).orElse(null);
    }

    @Override
    public Bike save(Bike bike) {
        if (findById(bike.getId()).isPresent()) {
            return null;
        } else {
            bikes.add(bike);
            updateStationAfterAddingBike(bike);
            return bike;
        }
    }

    private void updateStationAfterAddingBike(Bike addedBike) {
        String dockingStationId = Optional.ofNullable(addedBike.getDockingStationId()).orElse("");
        dockingStationRepository.findById(dockingStationId)
                .ifPresent(station -> station.getBikeIds().add(addedBike.getId()));
    }

    private void updateStationAfterRemovingBike(Bike removedBike) {
        String dockingStationId = Optional.ofNullable(removedBike.getDockingStationId()).orElse("");
        dockingStationRepository.findById(dockingStationId)
                .ifPresent(station -> station.getBikeIds().remove(removedBike.getId()));
    }

    @Override
    public void delete(String id) {
        findById(id).ifPresent(bike -> {
            updateStationAfterRemovingBike(bike);
            bikes = bikes.stream()
                    .filter(b -> !b.getId().equals(id))
                    .collect(Collectors.toList());
        });
    }

}
