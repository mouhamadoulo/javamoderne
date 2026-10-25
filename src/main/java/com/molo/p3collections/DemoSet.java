package com.molo.p3collections;

import java.util.*;

/**
 * Un Set interdit les doublons selon les règles d'égalité des objets (equals() et hashCode() pour les ensembles fondés sur le hachage).
 * HashSet : pas d'ordre garanti
 * LinkedHashSet : Ordre d'insertion
 * TreeSet : éléments triés
 */
public class DemoSet {
    public static void main(String[] args) {
        Set<String> hash = new HashSet<>(
                List.of("Paris", "Lyon", "Paris"));
        System.out.println(hash); // Doublon éliminé, ordre non garanti

        Set<String> insertion = new LinkedHashSet<>();
        insertion.add("Lyon");
        insertion.add("Paris");
        insertion.add("Lyon");
        System.out.println(insertion); // [Lyon, Paris]

        Set<Integer> trie = new TreeSet<>(
                List.of(5, 2, 8, 2, 1));
        System.out.println(trie); // [1, 2, 5, 8]

        trie.add(3);
        trie.remove(2);
        System.out.println(trie.contains(5)); // true
    }
}

