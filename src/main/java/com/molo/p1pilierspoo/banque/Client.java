package com.molo.p1pilierspoo.banque;

/**
 * Concepts vus ici : héritage, encapsulation et redéfinition de méthodes
 * Points importants :
 * - extends Personne signifie que Client hérite de Personne.
 * - super(...) appelle le constructeur de la classe mère.
 * - @Override indique que l'on redéfinit une méthode héritée.
 * - Le setter valide les données : c'est un exemple d'encapsulation qui protège les règles métier.
 */
public class Client extends Personne {

    private String email;

    public Client(String nom, String prenom,
                  String numeroIdentite, String email) {
        super(nom, prenom, numeroIdentite);
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException(
                    "Adresse email invalide"
            );
        }
        this.email = email;
    }

    @Override
    public void afficherProfil() {
        System.out.println("Client : " + getNomComplet());
        System.out.println("Identité : " + getNumeroIdentite());
        System.out.println("Email : " + email);
    }
}
