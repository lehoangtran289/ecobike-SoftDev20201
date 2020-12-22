package com.hust.ebr.repository.impl;

import com.hust.ebr.model.Rental;
import com.hust.ebr.repository.RentalRepository;
import com.hust.ebr.repository.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
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
