package com.molo.p8designpatterns.dp3comportement.strategy;

import java.math.BigDecimal;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paiement PayPal : " + amount + " €");
    }
}
