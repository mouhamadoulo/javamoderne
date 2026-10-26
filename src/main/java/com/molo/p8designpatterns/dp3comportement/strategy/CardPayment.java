package com.molo.p8designpatterns.dp3comportement.strategy;

import java.math.BigDecimal;

public class CardPayment implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paiement par carte : " + amount + " €");
    }
}