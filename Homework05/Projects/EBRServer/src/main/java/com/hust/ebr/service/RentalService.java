package com.hust.ebr.service;

import com.hust.ebr.model.Rental;
import com.hust.ebr.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<Rental> getRentals(Rental rental) {
        return rentalRepository.search(rental);
    }

    public List<Rental> getRentalsByCardNumber(String cardNumber) {
        return rentalRepository.findByCardNumber(cardNumber);
    }

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}
