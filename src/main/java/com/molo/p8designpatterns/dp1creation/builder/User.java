package com.molo.p8designpatterns.dp1creation.builder;

/**
 * Pattern Builder — construire un objet progressivement
 * Cas concret : créer un utilisateur avec des champs obligatoires et des options facultatives, sans multiplier les constructeurs.
 * À retenir :
 * - Builder sépare la construction de la représentation finale.
 * - Les méthodes chaînées retournent this.
 * - build() valide les données et construit l'objet.
 * - Les attributs final rendent l'objet User immuable après sa création.
 */
public class User {

    private final String nom;
    private final String email;
    private final int age;

    private User(Builder builder) {
        this.nom = builder.nom;
        this.email = builder.email;
        this.age = builder.age;
    }

    public static class Builder {
        private final String nom;
        private final String email;
        private int age = 18;

        public Builder(String nom, String email) {
            this.nom = nom;
            this.email = email;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            if (nom.isBlank() || email.isBlank()) {
                throw new IllegalArgumentException(
                        "Nom et email obligatoires"
                );
            }
            if (age < 0) {
                throw new IllegalArgumentException("Âge invalide");
            }
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return nom + " | " + email + " | " + age + " ans";
    }
}