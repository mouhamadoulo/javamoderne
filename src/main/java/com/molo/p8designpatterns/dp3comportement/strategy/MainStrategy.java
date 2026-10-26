package com.molo.p8designpatterns.dp3comportement.strategy;
import java.math.BigDecimal;

/**
 * Strategy — changer un algorithme sans modifier le client
 * Encapsuler plusieurs algorithmes derrière une même interface et choisir celui à utiliser à l'exécution.
 * Exemple concret : une application de paiement peut utiliser une carte bancaire ou PayPal sans modifier son service de paiement.
 * Quand utiliser Strategy ?
 * - Plusieurs modes de paiement.
 * - Plusieurs algorithmes de calcul de prix ou de remise.
 * - Plusieurs stratégies de tri, de compression ou de traitement.
 * - Remplacer une longue chaîne de if/else ou de switch par des comportements interchangeables.
 * Limite et bonne pratique : chaque stratégie doit représenter un comportement réellement distinct. Pour un système de paiement réel, il faut aussi gérer les erreurs, la sécurité, l'idempotence et la confirmation de transaction.
 */
public class MainStrategy {

    public static void main(String[] args) {

        var amount = new BigDecimal("99.90");

        var cardService = new PaymentService(new CardPayment());
        cardService.checkout(amount);

        var paypalService = new PaymentService(new PaypalPayment());
        paypalService.checkout(amount);
    }
}
