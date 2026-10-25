package com.molo.p3collections;

import java.util.*;

/**
 * Collections immuables et copies
 * En Java moderne, il est important de comprendre la différence entre une collection non modifiable et une copie indépendante.
 */
public class DemoImmutable {
    public static void main(String[] args) {
        // Collection non modifiable
        List<String> noms = List.of("Ali", "Fatou");
        // noms.add("Moussa"); // UnsupportedOperationException

        // Copie indépendante et non modifiable
        List<String> copie = List.copyOf(noms);

        // Vue non modifiable d'une liste modifiable
        List<String> source =
                new ArrayList<>(List.of("Paris", "Lyon"));
        List<String> vue =
                Collections.unmodifiableList(source);

        source.add("Lille");
        System.out.println(vue); // [Paris, Lyon, Lille]

        // Copie modifiable
        List<String> modifiable = new ArrayList<>(noms);
        modifiable.add("Moussa");
        System.out.println(modifiable);
    }
}

