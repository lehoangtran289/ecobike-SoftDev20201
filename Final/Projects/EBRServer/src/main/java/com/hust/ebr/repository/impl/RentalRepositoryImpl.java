package com.hust.ebr.repository.impl;

import com.hust.ebr.model.*;
import com.hust.ebr.repository.RentalRepository;
import com.hust.ebr.repository.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {

    private final Seed seed;
    private List<Rental> rentals;

    @PostConstruct
    public void init() {
        rentals = seed.getRentals();
    }

    @Override
    public List<Rental> search(Rental rental) {
        return rentals.stream()
                .filter(r -> r.match(rental))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> search(List<String> types, Rental rental) {
        List<Rental> result = new ArrayList<>();
        for (String type : types) {
            switch (type) {
                case "normalBike":
                    result.addAll(rentals.stream()
                            .filter(r -> r.getBikeType() == Rental.Type.NormalBike)
                            .filter(r -> r.match(rental))
                            .collect(Collectors.toList()));
                    break;
                case "twinBike":
                    result.addAll(rentals.stream()
                            .filter(r -> r.getBikeType() == Rental.Type.TwinBike)
                            .filter(r -> r.match(rental))
                            .collect(Collectors.toList()));
                    break;
                case "eBike":
                    result.addAll(rentals.stream()
                            .filter(r -> r.getBikeType() == Rental.Type.EBike)
                            .filter(r -> r.match(rental))
                            .collect(Collectors.toList()));
                    break;
                default:
                    result.addAll(rentals.stream()
                            .filter(r -> r.match(rental))
                            .collect(Collectors.toList()));
                    break;
            }
        }
        return result;
    }

    @Override
    public List<Rental> findByCardNumber(String cardNumber) {
        return rentals.stream()
                .filter(r -> r.getCardNumber().equals(cardNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Rental save(Rental rental) {
//        Optional.ofNullable(rental).ifPresent(r -> rentals.add(r));
//        return rental;
        if (rental != null) {
            rentals.add(rental);
            return rental;
        }
        return null;
    }
}
