package com.molo.p4progfonc;

import java.util.Optional;

/**
 * Optional : gérer l'absence de valeur
 * À connaître :
 * - Optional.of(value) : la valeur doit être non nulle.
 * - Optional.ofNullable(value) : accepte une valeur potentiellement nulle.
 * - Optional.empty() : représente l'absence de valeur.
 * - map() : transforme la valeur présente.
 * - flatMap() : transforme sans imbriquer un autre Optional.
 * - orElse() et orElseGet() : fournissent une valeur de remplacement.
 *
 * En pratique, Optional est particulièrement utile pour exprimer un résultat potentiellement absent,
 * notamment dans les valeurs de retour.
 */
public class OptionalExamples {

    public static void demo() {

        Optional<String> nom = Optional.of("Moussa");
        Optional<String> absent = Optional.empty();

        // 1. Valeur par défaut
        System.out.println(absent.orElse("Inconnu")); // Inconnu

        // 2. Transformer la valeur
        System.out.println(
                nom.map(String::toUpperCase).orElse("INCONNU")
        ); // MOUSSA

        // 3. Filtrer une valeur
        System.out.println(
                nom.filter(n -> n.length() > 5).orElse("Court")
        ); // Moussa

        // 4. Exécuter une action si présent
        nom.ifPresent(System.out::println); // Moussa

        // 5. Fournir une valeur par défaut calculée à la demande
        System.out.println(
                absent.orElseGet(() -> "Valeur calculée")
        );
    }
}
