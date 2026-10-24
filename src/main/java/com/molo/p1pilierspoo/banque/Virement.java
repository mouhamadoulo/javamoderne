package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : implémentation d'interface, composition et collaboration entre objets.
 */
public class Virement implements Transaction {

    private final CompteBancaire source;
    private final CompteBancaire destination;
    private final double montant;

    public Virement(CompteBancaire source,
                    CompteBancaire destination,
                    double montant) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException(
                    "Les deux comptes sont obligatoires"
            );
        }
        if (source == destination) {
            throw new IllegalArgumentException(
                    "Les comptes doivent être différents"
            );
        }
        if (!Double.isFinite(montant) || montant <= 0) {
            throw new IllegalArgumentException(
                    "Montant invalide"
            );
        }

        this.source = source;
        this.destination = destination;
        this.montant = montant;
    }

    @Override
    public void executer() {
        // On retire d'abord l'argent de la source.
        source.retirer(montant);

        // Puis on le crédite sur le compte destinataire.
        destination.deposer(montant);

        System.out.println(getDescription());
    }

    @Override
    public String getDescription() {
        return String.format(
                "Virement de %.2f € : %s -> %s",
                montant,
                source.getNumero(),
                destination.getNumero()
        );
    }
}

