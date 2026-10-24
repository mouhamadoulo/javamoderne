package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : interface, abstraction et polymorphisme.
 * Une interface définit un contrat que différentes classes peuvent implémenter.
 * Cette interface impose deux méthodes à toute classe qui l'implémente.
 * Une transaction pourra être un virement, un paiement ou un remboursement, sans que le code appelant ait besoin de connaître son implémentation précise.
 */
public interface Transaction {

    void executer();

    String getDescription();
}
