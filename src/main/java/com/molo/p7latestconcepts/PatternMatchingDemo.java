package com.molo.p7latestconcepts;

/**
 * Pattern Matching et Switch Patterns
 * Le pattern matching permet de tester le type d'un objet et de récupérer directement une variable du bon type, sans cast manuel.
 * Les différences importantes :
 * Concept	Rôle	Exemple
 * Pattern matching	Tester le type et déclarer une variable	instanceof String texte
 * Switch patterns	Brancher selon le type ou la forme de la valeur	case String texte ->
 * Garde when	Ajouter une condition au pattern	case Integer n when n < 0 ->
 * case null	Traiter explicitement null dans le switch	case null ->
 */
public class PatternMatchingDemo {

    static String decrire(Object objet) {

        // 1. Pattern matching avec instanceof
        if (objet instanceof String texte) {
            return "Texte : " + texte.toUpperCase();
        }

        if (objet instanceof Integer nombre && nombre > 0) {
            return "Entier positif : " + nombre;
        }

        // 2. Switch patterns (Java 21+)
        return switch (objet) {
            case null -> "Valeur absente";

            case String texte ->
                    "String de " + texte.length() + " caractères";

            case Integer nombre when nombre < 0 ->
                    "Entier négatif : " + nombre;

            case Integer nombre ->
                    "Entier : " + nombre;

            case Double nombre ->
                    "Décimal : " + nombre;

            default -> "Type non reconnu";
        };
    }

    public static void main(String[] args) {
        System.out.println(decrire("Java"));
        System.out.println(decrire(25));
        System.out.println(decrire(-5));
        System.out.println(decrire(3.14));
        System.out.println(decrire(null));
    }
}
