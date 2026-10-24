package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : abstraction, encapsulation, héritage
 * Une personne est une notion générale, on définit ses caractéristiques communes mais on ne crée pas directement une instance de Personne
 * à retenir :
 * - abstract empêche l'instanciation directe de cette classe.
 * - private protège les attributs contre les accès directs depuis les autres classes.
 * - final interdit de réaffecter les attributs après leur initialisation.
 * - protected permet aux sous-classes d'appeler le constructeur.
 * - afficherProfil() est une méthode abstraite que les sous-classes devront implémenter.
 */
public abstract class Personne {

    private final String nom;
    private final String prenom;
    private final String numeroIdentite;

    protected Personne(String nom, String prenom, String numeroIdentite) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroIdentite = numeroIdentite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroIdentite() {
        return numeroIdentite;
    }

    public abstract void afficherProfil();

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
