package com.molo.p8designpatterns.dp2structure.facade;

/**
 * Objectif : simplifier l'utilisation d'un système composé de plusieurs sous-systèmes.
 * Sans facade, le client doit piloter séparément le lecteur vidéo et le projecteur. Avec une facade, un seul appel suffit.
 * À retenir :
 * - LecteurVideo et Projecteur sont les sous-systèmes.
 * - HomeCinemaFacade fournit une interface simplifiée.
 * - Le client n'a pas besoin de connaître l'ordre des opérations internes.
 * - La facade ne supprime pas les sous-systèmes : ils restent accessibles si nécessaire.
 * Exemple professionnel : une facade Spring Boot peut orchestrer plusieurs services internes pour exposer une opération métier simple à un contrôleur REST.
 */
public class HomeCinemaFacade {

    private final LecteurVideo lecteur;
    private final Projecteur projecteur;

    public HomeCinemaFacade() {
        this.lecteur = new LecteurVideo();
        this.projecteur = new Projecteur();
    }

    public void regarderFilm() {
        projecteur.allumer();
        lecteur.lire();
    }

    public static void main(String[] args) {
        HomeCinemaFacade homeCinema = new HomeCinemaFacade();
        homeCinema.regarderFilm();
    }
}
