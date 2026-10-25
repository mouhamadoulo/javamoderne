package com.molo.p4progfonc;

import java.util.function.*;

/**
 * Les 5 interfaces à maîtriser en priorité :
 * Function<T,R>	Transformer	apply()
 * Predicate<T>	Tester	test()
 * Consumer<T>	Consommer	accept()
 * Supplier<T>	Fournir	get()
 * UnaryOperator<T>	Transformer le même type	apply()
 *
 * BinaryOperator<T> est également très utile pour les opérations entre deux valeurs de même type.
 */
public class FunctionExamples {

    public static void demo() {

        // Function<T, R> : transforme une valeur
        Function<String, Integer> longueur = String::length;
        System.out.println(longueur.apply("Java")); // 4

        // Predicate<T> : vérifie une condition
        Predicate<Integer> majeur = age -> age >= 18;
        System.out.println(majeur.test(25)); // true

        // Consumer<T> : consomme une valeur
        Consumer<String> afficher = System.out::println;
        afficher.accept("Bonjour Java");

        // Supplier<T> : fournit une valeur
        Supplier<Double> aleatoire = Math::random;
        System.out.println(aleatoire.get());

        // UnaryOperator<T> : transforme un type en lui-même
        UnaryOperator<Integer> carre = n -> n * n;
        System.out.println(carre.apply(5)); // 25

        // BinaryOperator<T> : combine deux valeurs du même type
        BinaryOperator<Integer> somme = Integer::sum;
        System.out.println(somme.apply(3, 7)); // 10

        // Composition de fonctions
        Function<Integer, Integer> doubler = n -> n * 2;
        Function<Integer, Integer> ajouterUn = n -> n + 1;

        System.out.println(doubler.andThen(ajouterUn).apply(5)); // 11
        System.out.println(doubler.compose(ajouterUn).apply(5)); // 12
    }
}
