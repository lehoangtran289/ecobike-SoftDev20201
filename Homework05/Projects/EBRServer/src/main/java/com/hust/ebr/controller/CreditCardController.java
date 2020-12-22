package com.hust.ebr.controller;

import com.hust.ebr.model.CreditCard;
import com.hust.ebr.model.DockingStation;
import com.hust.ebr.model.dto.request.CreditCardReqDTO;
import com.hust.ebr.model.dto.request.RequestType;
import com.hust.ebr.model.dto.response.DockingStationResDTO;
import com.hust.ebr.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping("/credit-cards/{cardNumber}")
    public CreditCard getCreditCardInfo(@PathVariable("cardNumber") String cardNumber) {
        return StringUtils.hasText(cardNumber) ? creditCardService.getByCardNumber(cardNumber) : null;
    }

    @GetMapping("/credit-cards")
    public List<CreditCard> getDockingStations(CreditCard creditCard) {
        return creditCardService.getCreditCards(creditCard);
    }

    @PostMapping("/{cardNumber}")
    public CreditCard creditCardAction(@PathVariable("cardNumber") String cardNumber,
                                       @RequestBody CreditCardReqDTO req) {
        if (StringUtils.hasText(cardNumber) && Objects.nonNull(req)) {
            if (req.getType() == RequestType.Refund) {
                return creditCardService.refund(cardNumber, req.getAmount());
            } else if (req.getType() == RequestType.Deduct) {
                return creditCardService.deduct(cardNumber, req.getAmount());
            }
        }
        return null;
    }
}
