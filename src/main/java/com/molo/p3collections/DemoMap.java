package com.molo.p3collections;

import java.util.*;

/**
 * Une Map associe une clé unique à une valeur. Elle ne fait pas partie de l'interface Collection.
 * Les trois implémentations à connaître :
 * - HashMap : usage général, ordre non garanti.
 * - LinkedHashMap : conserve l'ordre d'insertion par défaut.
 * - TreeMap : trie les clés.
 * Attention : HashMap accepte une clé null et des valeurs null, contrairement à Map.of().
 */
public class DemoMap {
    public static void main(String[] args) {
        Map<String, Integer> notes = new HashMap<>();

        notes.put("Ali", 15);
        notes.put("Fatou", 18);
        notes.put("Ali", 17); // Remplace la valeur précédente

        System.out.println(notes.get("Ali")); // 17
        System.out.println(notes.getOrDefault("Moussa", 0)); // 0

        notes.putIfAbsent("Moussa", 12);
        notes.merge("Ali", 1, Integer::sum); // 17 + 1

        // Parcourir clés et valeurs
        notes.forEach((nom, note) ->
                System.out.println(nom + " : " + note));

        // Parcourir les entrées
        for (Map.Entry<String, Integer> entry
                : notes.entrySet()) {
            System.out.println(entry.getKey()
                    + " = " + entry.getValue());
        }


        notes.remove("Fatou");
        System.out.println(notes.containsKey("Ali"));
        System.out.println(notes.values());
    }
}

