package com.molo.p1pilierspoo.banque;

/**
 * Classe de Test
 */
public class Main {

    public static void main(String[] args) {

        // 1. Création des clients
        Client client1 = new Client(
                "Diallo", "Amadou", "ID001",
                "amadou@example.com"
        );

        Client client2 = new Client(
                "Ndiaye", "Fatou", "ID002",
                "fatou@example.com"
        );

        client1.afficherProfil();
        System.out.println();

        // 2. Création des comptes
        CompteCourant courant = new CompteCourant(
                "C001", client1, 1000.0, 500.0
        );

        CompteEpargne epargne = new CompteEpargne(
                "E001", client1, 2000.0, 0.03
        );

        CompteCourant compteBeneficiaire =
                new CompteCourant(
                        "C002", client2, 100.0, 200.0
                );

        // 3. Encapsulation : opérations contrôlées
        courant.deposer(200.0);
        courant.retirer(50.0);

        System.out.printf(
                "Solde courant : %.2f €%n",
                courant.getSolde()
        );

        // 4. Héritage et polymorphisme
        CompteBancaire compte = epargne;

        System.out.printf(
                "Intérêts de l'épargne : %.2f €%n",
                compte.calculerInterets()
        );

        // 5. Polymorphisme : même méthode,
        // comportements différents
        CompteBancaire[] comptes = {
                courant, epargne, compteBeneficiaire
        };

        System.out.println("\n=== Tous les comptes ===");

        for (CompteBancaire c : comptes) {
            c.afficherDetails();
            System.out.println();
        }

        // 6. Abstraction par interface
        Transaction transaction = new Virement(
                epargne, compteBeneficiaire, 100.0
        );

        transaction.executer();

        // 7. Application des intérêts
        epargne.appliquerInterets();

        // 8. Composition / gestion des comptes
        Banque banque = new Banque("Molo Bank");

        banque.ajouterCompte(courant);
        banque.ajouterCompte(epargne);
        banque.ajouterCompte(compteBeneficiaire);

        banque.afficherTousLesComptes();

        System.out.printf(
                "Total des soldes : %.2f €%n",
                banque.calculerTotalSoldes()
        );

        // 9. Recherche d'un compte
        banque.rechercherCompte("E001")
                .ifPresentOrElse(
                        c -> System.out.println(
                                "Compte trouvé : " + c.getNumero()
                        ),
                        () -> System.out.println(
                                "Compte introuvable"
                        )
                );

        // 10. Gestion d'une erreur métier
        try {
            courant.retirer(100_000.0);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Opération refusée : " + e.getMessage()
            );
        }
    }
}
