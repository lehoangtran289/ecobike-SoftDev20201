package com.hust.ebr.repository;

import com.hust.ebr.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    List<CreditCard> getCreditCardNumbers(CreditCard creditCard);

    List<CreditCard> search(CreditCard creditCard);

    Optional<CreditCard> findByCardNumber(String cardNumber);

    CreditCard update(CreditCard creditCard);
}
