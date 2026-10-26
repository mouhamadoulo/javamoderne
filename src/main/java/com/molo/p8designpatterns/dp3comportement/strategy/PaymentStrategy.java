package com.molo.p8designpatterns.dp3comportement.strategy;

import java.math.BigDecimal;

/**
 * L'interface définit le contrat commun aux différentes stratégies.
 */
public interface PaymentStrategy {
    void pay(BigDecimal amount);
}