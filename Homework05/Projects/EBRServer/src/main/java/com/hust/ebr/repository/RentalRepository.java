package com.hust.ebr.repository;

import com.hust.ebr.model.Rental;

import java.util.List;

public interface RentalRepository {
    List<Rental> search(Rental rental);
    List<Rental> findByCardNumber(String cardNumber);
    Rental save(Rental rental);
}
