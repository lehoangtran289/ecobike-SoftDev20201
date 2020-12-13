package com.hust.ebr.repository;

import com.hust.ebr.model.DockingStation;
import com.hust.ebr.repository.generic.ICrudRepository;

import java.util.List;
import java.util.Optional;

public interface DockingStationRepository extends ICrudRepository<DockingStation, String> {
    List<DockingStation> search(DockingStation dockingStation);

    Optional<DockingStation> findById(String id);

    DockingStation update(DockingStation dockingStation);

    DockingStation save(DockingStation dockingStation);
}
