package com.hust.ebr.repository;

import com.hust.ebr.model.Bike;
import com.hust.ebr.repository.generic.ICrudRepository;

import java.util.List;
import java.util.Optional;

public interface BikeRepository extends ICrudRepository<Bike, String> {
    List<Bike> search(Bike bike);

    Optional<Bike> findById(String id);

    Bike update(Bike bike);

    Bike save(Bike bike);

    void delete(String id);
}
