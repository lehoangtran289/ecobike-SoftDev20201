package com.hust.ebr.service;

import com.hust.ebr.model.DockingStation;
import com.hust.ebr.model.dto.response.DockingStationResDTO;
import com.hust.ebr.repository.DockingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DockingStationService {

    private final DockingStationRepository dockingStationRepository;
    private final BikeService bikeService;

    public List<DockingStationResDTO> getDockingStations(DockingStation dockingStation) {
        return dockingStationRepository.search(dockingStation)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DockingStationResDTO findById(String id) {
        return dockingStationRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public DockingStationResDTO update(DockingStation dockingStation) {
        return convertToDTO(dockingStationRepository.update(dockingStation));
    }

    public DockingStationResDTO save(DockingStation dockingStation) {
        return StringUtils.hasText(dockingStation.getId()) ?
                convertToDTO(dockingStationRepository.save(dockingStation)) :
                null;
    }

    public void deleteDockingStation(String id) {
        if (!StringUtils.hasText(id))
            throw new NullPointerException("id is null or empty!");
        dockingStationRepository.delete(id);
    }

    public DockingStationResDTO convertToDTO(DockingStation dockingStation) {
        List<String> currentBikeIds = dockingStation.getBikeIds();
        int normalBikesCount = (int) bikeService.getNormalBikeIds().stream()
                .filter(currentBikeIds::contains)
                .count();
        int eBikesCount = (int) bikeService.getEBikeIds().stream()
                .filter(currentBikeIds::contains)
                .count();
        int twinBikesCount = (int) bikeService.getTwinBikeIds().stream()
                .filter(currentBikeIds::contains)
                .count();

        DockingStationResDTO res = new DockingStationResDTO();
        res.setId(dockingStation.getId());
        res.setStationAddress(dockingStation.getStationAddress());
        res.setStationName(dockingStation.getStationName());
        res.setTotalDockCount(dockingStation.getTotalDockCount());
        res.setBikeIds(currentBikeIds);
        res.setNormalBikeCount(normalBikesCount);
        res.setEBikeCount(eBikesCount);
        res.setTwinBikeCount(twinBikesCount);
        res.setEmptyDockCount(dockingStation.getTotalDockCount() - normalBikesCount - eBikesCount - twinBikesCount);

        return res;
    }

}
