package com.molo.p5exceptions;

import java.io.IOException;

/**
 * Les concepts à connaître absolument :
 * Checked exceptions
 * - vérifiées à la compilation
 * - Exemples : IOException, SQLException, SoldeInsuffisantException.
 * - Il faut les intercepter avec catch ou les déclarer avec throws.
 * Unchecked exceptions
 * - sous classes de RuntimeException
 * - Exemples : NullPointerException, IllegalArgumentException, ArithmeticException.
 * - Il n'est pas obligatoire de les intercepter ni de les déclarer.
 */
public class MainExceptions {

    public static void main(String[] args) {

        // 1. try / catch : gérer une exception
        try {
            int resultat = 10 / 0;
            System.out.println(resultat);
        } catch (ArithmeticException e) {
            System.out.println("Division impossible : " + e.getMessage());
        }

        // 2. Plusieurs catch : du plus spécifique au plus général
        try {
            int[] nombres = {10, 20};
            System.out.println(nombres[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index invalide");
        } catch (RuntimeException e) {
            System.out.println("Erreur d'exécution");
        }

        // 3. Multi-catch : traiter plusieurs exceptions de la même façon
        try {
            int nombre = Integer.parseInt("abc");
            System.out.println(10 / nombre);
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Calcul impossible : " + e.getMessage());
        }

        // 4. Exception personnalisée : checked
        CompteBancaire compte = new CompteBancaire(100);

        try {
            compte.retirer(150);
        } catch (SoldeInsuffisantException e) {
            System.out.println("Retrait refusé : " + e.getMessage());
        } finally {
            System.out.println("Solde actuel : " + compte.getSolde());
        }

        // 5. Exception unchecked : pas besoin de la déclarer avec throws
        try {
            compte.retirer(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Montant invalide : " + e.getMessage());
        } catch (SoldeInsuffisantException e) {
            System.out.println("Solde insuffisant : "+ e.getMessage());
        }

        // 6. try-with-resources : fermeture automatique
        try (var reader = new java.io.StringReader("Java 25")) {
            System.out.println("Caractère lu : " + (char) reader.read());
        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        // 7. Propagation : une méthode peut transmettre l'exception
        try {
            verifierAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    static void verifierAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Âge négatif");
        }
    }
}

