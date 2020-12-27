package com.hust.ebr.service;

import com.hust.ebr.model.Bike;
import com.hust.ebr.model.EBike;
import com.hust.ebr.model.NormalBike;
import com.hust.ebr.model.TwinBike;
import com.hust.ebr.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BikeService {
    private final BikeRepository bikeRepository;

    public List<Bike> getBikes(Bike bike) {
        return bikeRepository.search(bike);
    }

    public List<Bike> getBikes(List<String> types, Bike bike) {
        return bikeRepository.search(types, bike);
    }

    public Bike findById(String id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike update(Bike bike) {
        return bikeRepository.update(bike);
    }

    public Bike save(Bike bike) {
        return StringUtils.hasText(bike.getId()) ? bikeRepository.save(bike) : null;
    }

    public void deleteBike(String id) {
        if (!StringUtils.hasText(id))
            throw new NullPointerException("id is null or empty!");
        bikeRepository.delete(id);
    }

    public List<String> getBikeIds() {
        return bikeRepository.search(null)
                .stream()
                .map(Bike::getId)
                .collect(Collectors.toList());
    }

    public List<String> getEBikeIds() {
        return bikeRepository.search(null)
                .stream()
                .filter(b -> b instanceof EBike)
                .map(Bike::getId)
                .collect(Collectors.toList());
    }

    public List<String> getNormalBikeIds() {
        return bikeRepository.search(null)
                .stream()
                .filter(b -> b instanceof NormalBike)
                .map(Bike::getId)
                .collect(Collectors.toList());
    }

    public List<String> getTwinBikeIds() {
        return bikeRepository.search(null)
                .stream()
                .filter(b -> b instanceof TwinBike)
                .map(Bike::getId)
                .collect(Collectors.toList());
    }
}
