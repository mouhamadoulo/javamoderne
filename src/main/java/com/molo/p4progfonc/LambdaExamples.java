package com.molo.p4progfonc;

import java.util.List;
import java.util.function.Predicate;

/**
 * À retenir :
 * - Une lambda fournit l'implémentation d'une interface fonctionnelle.
 * - @FunctionalInterface garantit qu'une seule méthode abstraite est déclarée.
 * - -> sépare les paramètres du corps de la lambda.
 * - System.out::println est une référence de méthode, alternative à x -> System.out.println(x).
 */
public class LambdaExamples {

    // Interface fonctionnelle personnalisée
    @FunctionalInterface
    interface Operation {
        int calculer(int a, int b);
    }

    public static void demo() {
        // 1. Lambda simple
        Operation addition = (a, b) -> a + b;
        System.out.println(addition.calculer(3, 5)); // 8

        // 2. Lambda avec plusieurs instructions
        Operation multiplication = (a, b) -> {
            int resultat = a * b;
            return resultat;
        };
        System.out.println(multiplication.calculer(3, 5)); // 15

        // 3. Predicate : tester une condition
        Predicate<Integer> estPair = n -> n % 2 == 0;
        System.out.println(estPair.test(4)); // true

        // 4. Référence de méthode
        List.of("Java", "Spring", "Angular")
                .forEach(System.out::println);
    }
}
