package com.molo.p1pilierspoo.banque;

/**
 * Cette classe représente le modèle commun à tous les comptes bancaires.
 * Concepts vus ici : abstraction, encapsulation, méthodes abstraites et polymorphisme.
 * Ce que tu dois comprendre :
 * - solde est privé : il ne peut pas être modifié directement depuis Main.
 * - deposer() et retirer() exposent des opérations contrôlées.
 * - calculerInterets() est abstraite : chaque type de compte définit sa propre règle.
 * - peutRetirer() est une méthode protégée que les sous-classes peuvent redéfinir.
 * - appliquerInterets() illustre le Template Method : la classe mère orchestre l'opération, tandis que le calcul est délégué à la sous-classe.
 */
public abstract class CompteBancaire {

    private final String numero;
    private double solde;
    private final Client titulaire;

    public CompteBancaire(String numero, Client titulaire,
                          double soldeInitial) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException(
                    "Numéro de compte obligatoire"
            );
        }
        if (titulaire == null) {
            throw new IllegalArgumentException(
                    "Le titulaire est obligatoire"
            );
        }
        if (!Double.isFinite(soldeInitial) || soldeInitial < 0) {
            throw new IllegalArgumentException(
                    "Solde initial invalide"
            );
        }

        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public Client getTitulaire() {
        return titulaire;
    }

    public void deposer(double montant) {
        verifierMontant(montant);
        solde += montant;
    }

    public void retirer(double montant) {
        verifierMontant(montant);

        if (!peutRetirer(montant)) {
            throw new IllegalArgumentException(
                    "Solde insuffisant"
            );
        }

        solde -= montant;
    }

    protected boolean peutRetirer(double montant) {
        return solde >= montant;
    }

    protected static void verifierMontant(double montant) {
        if (!Double.isFinite(montant) || montant <= 0) {
            throw new IllegalArgumentException(
                    "Le montant doit être positif et fini"
            );
        }
    }

    public abstract double calculerInterets();

    public void appliquerInterets() {
        double interets = calculerInterets();

        if (!Double.isFinite(interets) || interets < 0) {
            throw new IllegalStateException(
                    "Intérêts calculés invalides"
            );
        }

        if (interets > 0) {
            deposer(interets);
        }
    }

    public void afficherDetails() {
        System.out.println("Compte : " + numero);
        System.out.println("Titulaire : "
                + titulaire.getNomComplet());
        System.out.printf("Solde : %.2f €%n", solde);
        System.out.printf("Intérêts calculés : %.2f €%n",
                calculerInterets());
    }
}
