package com.hust.ebr.repository;

import com.hust.ebr.model.Bike;
import com.hust.ebr.repository.generic.ICrudRepository;

import java.util.List;

public interface BikeRepository extends ICrudRepository<Bike, String> {
    List<Bike> search(List<String> type, Bike bike);
}
