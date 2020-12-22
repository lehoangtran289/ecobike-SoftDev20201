package com.hust.ebr.repository.impl;

import com.hust.ebr.model.CreditCard;
import com.hust.ebr.repository.CreditCardRepository;
import com.hust.ebr.repository.seed.Seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final Seed seed;
    private List<CreditCard> creditCards;

    @PostConstruct
    public void init() {
        creditCards = seed.getCreditCards();
    }

    @Override
    public List<CreditCard> search(CreditCard creditCard) {
        return creditCards.stream()
                .filter(c -> c.match(creditCard))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CreditCard> findByCardNumber(String cardNumber) {
        return creditCards.stream()
                .filter(b -> b.getCardNumber().equals(cardNumber))
                .findAny();
    }

    @Override
    public CreditCard update(CreditCard creditCard) {
        creditCards = creditCards.stream()
                .map(card -> card.equals(creditCard) ? creditCard : card)
                .collect(Collectors.toList());
        return findByCardNumber(creditCard.getCardNumber()).orElse(null);
    }

    @Override
    public List<CreditCard> getCreditCardNumbers(CreditCard creditCard) {
        return creditCards.stream()
                .filter(c -> c.match(creditCard))
                .collect(Collectors.toList());
    }

}
