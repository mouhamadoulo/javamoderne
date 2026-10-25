package com.molo.p7latestconcepts;

/**
 * Records et Sealed Classes
 * À retenir
 * - Record : génère automatiquement le constructeur, les accesseurs, equals(), hashCode() et toString().
 * - Un record est implicitement final : il ne peut pas être étendu.
 * - Ses composants sont des champs private final, mais cela ne garantit pas une immutabilité profonde si un composant référence un objet mutable.
 * - Sealed : avec permits, tu contrôles les classes autorisées à implémenter ou étendre un type.
 * - Une classe autorisée doit généralement être déclarée final, sealed ou non-sealed.
 */
// 1. RECORD : objet de données concis
record Produit(String nom, double prix) {
    // Le constructeur canonique et les accesseurs
    // sont générés automatiquement.
}

// 2. SEALED INTERFACE : hiérarchie contrôlée
sealed interface Paiement permits Carte, Virement {
    double montant();
}

// Seules les classes autorisées peuvent implémenter Paiement.
record Carte(double montant, String numero) implements Paiement {}
record Virement(double montant, String iban) implements Paiement {}

public class RecordsDemo {

    public static void main(String[] args) {
        // RECORD
        Produit produit = new Produit("Clavier", 49.99);

        System.out.println(produit.nom());
        System.out.println(produit.prix());
        System.out.println(produit); // toString() généré

        // SEALED CLASSES / INTERFACES
        Paiement paiement = new Carte(100, "1234");

        System.out.println(paiement.montant());
    }
}

