package com.molo.p8designpatterns.dp2structure.proxy;

public class DocumentReel implements Document {

    private final String nom;

    public DocumentReel(String nom) {
        this.nom = nom;
        charger();
    }

    private void charger() {
        System.out.println("Chargement du document : " + nom);
    }

    @Override
    public void afficher() {
        System.out.println("Affichage du document : " + nom);
    }
}