package com.molo.p8designpatterns.dp1creation.singleton;

/**
 * Pattern Singleton — une seule instance
 * Cas concret : une configuration partagée dans toute l'application.
 * À retenir :
 * - Constructeur privé : empêche la création directe.
 * - Champ static final : conserve l'instance unique.
 * - Initialisation à la demande ? Non : ici, l'instance est créée au chargement de la classe.
 * - Cette variante est thread-safe grâce à l'initialisation des classes Java.
 */
public final class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void afficher() {
        System.out.println("Instance unique");
    }
}
