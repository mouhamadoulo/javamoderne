package com.molo.p5exceptions;

/**
 * Cette classe illustre la validation, throw, throws et le chaînage d'exceptions.
 * Différence essentielle :
 * throw	Déclenche une exception.
 * throws	Déclare les exceptions qu'une méthode peut propager.
 * try	Encadre le code susceptible de lever une exception.
 * catch	Intercepte et traite une exception.
 * finally	Exécute le code de nettoyage, qu'une exception survienne ou non, sauf arrêt brutal du programme.
 */
public class CompteBancaire {

    private double solde;

    public CompteBancaire(double soldeInitial) {
        if (soldeInitial < 0) {
            throw new IllegalArgumentException(
                    "Le solde initial ne peut pas être négatif"
            );
        }
        this.solde = soldeInitial;
    }

    public void retirer(double montant)
            throws SoldeInsuffisantException {

        if (montant <= 0) {
            throw new IllegalArgumentException(
                    "Le montant doit être positif"
            );
        }

        if (montant > solde) {
            throw new SoldeInsuffisantException(
                    "Solde insuffisant : " + solde + " €"
            );
        }

        solde -= montant;
    }

    public double getSolde() {
        return solde;
    }
}

