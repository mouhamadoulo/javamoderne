package com.molo.p3collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exploiter les collections avec Stream API
 * Les Streams, introduits en Java 8, permettent de filtrer, transformer et agréger les données sans modifier directement la collection source.
 * Les opérations essentielles à réviser :
 * filter()	-> Filtrer
 * map()	-> Transformer
 * flatMap()	-> Aplatir des flux imbriqués
 * distinct()	-> Éliminer les doublons
 * sorted()	-> Trier
 * limit() / skip()	-> Limiter ou ignorer des éléments
 * anyMatch() / allMatch()	-> Tester une condition
 * reduce()	-> Agréger
 * collect()	-> Construire un résultat
 * toList()	-> Obtenir une liste non modifiable
 */
public class DemoStreams {
    public static void main(String[] args) {
        List<Integer> notes = List.of(12, 8, 17, 15, 9, 17);

        List<String> prenoms = new ArrayList<>();
        prenoms.add("Moustapha");
        prenoms.add("Amadou");
        prenoms.add("Jean");
        prenoms.add("Adama");
        prenoms.add("Annie");

        prenoms.stream()
                .filter(p -> p.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // Filtrer, trier et collecter
        List<Integer> reussites = notes.stream()
                .filter(n -> n >= 10)
                .distinct()
                .sorted()
                .toList();


        System.out.println(reussites); // [12, 15, 17]

        // Transformer
        List<String> libelles = notes.stream()
                .map(n -> "Note : " + n)
                .toList();

        // Calculer une moyenne
        double moyenne = notes.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.println(moyenne);

        // Vérifier une condition
        boolean tousReussis = notes.stream()
                .allMatch(n -> n >= 10);

        // Regrouper les notes par valeur
        var frequences = notes.stream()
                .collect(Collectors.groupingBy(
                        n -> n,
                        Collectors.counting()
                ));

        System.out.println(frequences);
        System.out.println(tousReussis);
    }
}
