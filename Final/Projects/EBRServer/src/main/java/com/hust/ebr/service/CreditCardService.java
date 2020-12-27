package com.hust.ebr.service;

import com.hust.ebr.model.CreditCard;
import com.hust.ebr.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCard getByCardNumber(String cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber).orElse(null);
    }

    public List<CreditCard> getCreditCards(CreditCard creditCard) {
        return creditCardRepository.search(creditCard);
    }

    public CreditCard updateCreditCardStatus(String cardNumber, Boolean isRenting) {
        return creditCardRepository.findByCardNumber(cardNumber)
                .map(c -> {
                    c.setIsRentingBike(isRenting);
                    return creditCardRepository.update(c);
                })
                .orElse(null);
    }

    public CreditCard refund(String cardNumber, double amount) {
        return creditCardRepository.findByCardNumber(cardNumber)
                .map(c -> {
                    c.setBalance(c.getBalance() + amount);
                    return creditCardRepository.update(c);
                })
                .orElse(null);
    }

    public CreditCard deduct(String cardNumber, double amount) {
        return creditCardRepository.findByCardNumber(cardNumber)
                .map(c -> {
                    if (c.getBalance() - amount >= 0) {
                        c.setBalance(c.getBalance() - amount);
                        return creditCardRepository.update(c);
                    }
                    return null;
                })
                .orElse(null);
    }

    public boolean isCardValid(String cardNumber) {
        return getCardNumbers().contains(cardNumber);
    }

    private List<String> getCardNumbers() {
        return creditCardRepository.getCreditCardNumbers(null)
                .stream()
                .map(CreditCard::getCardNumber)
                .collect(Collectors.toList());
    }


}
