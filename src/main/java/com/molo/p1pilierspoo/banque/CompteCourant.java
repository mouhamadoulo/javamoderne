package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : héritage et spécialisation du comportement.
 * Ici, le compte courant peut autoriser un retrait qui dépasse le solde disponible, dans la limite du découvert autorisé
 * La règle est modifiée sans réécrire toute la méthode retirer().
 */
public class CompteCourant extends CompteBancaire {

    private final double decouvertAutorise;

    public CompteCourant(String numero, Client titulaire,
                         double soldeInitial,
                         double decouvertAutorise) {
        super(numero, titulaire, soldeInitial);

        if (!Double.isFinite(decouvertAutorise)
                || decouvertAutorise < 0) {
            throw new IllegalArgumentException(
                    "Découvert invalide"
            );
        }

        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    protected boolean peutRetirer(double montant) {
        return getSolde() + decouvertAutorise >= montant;
    }

    @Override
    public double calculerInterets() {
        return 0.0;
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.printf("Découvert autorisé : %.2f €%n",
                decouvertAutorise);
    }
}
