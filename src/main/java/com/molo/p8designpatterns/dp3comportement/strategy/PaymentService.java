package com.molo.p8designpatterns.dp3comportement.strategy;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentService {

    private final PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public void checkout(BigDecimal amount) {
        strategy.pay(amount);
    }
}
