package com.hust.ebr.controller;

import com.hust.ebr.model.Rental;
import com.hust.ebr.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping("/rentals")
    public List<Rental> getRentals(@RequestParam(required = false) List<String> types, Rental rental) {
        return types != null && types.size() > 0 ?
                rentalService.getRentals(types, rental) :
                rentalService.getRentals(rental);
    }

    @GetMapping("/rentals/{cardNumber}")
    public List<Rental> getRentalsByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        return StringUtils.hasText(cardNumber) ? rentalService.getRentalsByCardNumber(cardNumber) : null;
    }

    @PostMapping("/rentals")
    public Rental saveRental(@RequestBody Rental rental) {
        return rentalService.save(rental);
    }
}
