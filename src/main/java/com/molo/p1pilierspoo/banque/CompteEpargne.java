package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : héritage, polymorphisme et redéfinition.
 * Avec un solde de 1 000 € et un taux de 3 %, la méthode calcule 30 € d'intérêts pour la période considérée
 */
public class CompteEpargne extends CompteBancaire {

    private final double tauxInteret;

    public CompteEpargne(String numero, Client titulaire,
                         double soldeInitial,
                         double tauxInteret) {
        super(numero, titulaire, soldeInitial);

        if (!Double.isFinite(tauxInteret)
                || tauxInteret < 0 || tauxInteret > 1) {
            throw new IllegalArgumentException(
                    "Le taux doit être compris entre 0 et 1"
            );
        }

        this.tauxInteret = tauxInteret;
    }

    @Override
    public double calculerInterets() {
        return getSolde() * tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.printf("Taux : %.2f%%%n",
                tauxInteret * 100);
    }
}

