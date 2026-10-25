package com.molo.p4progfonc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Les Streams : filtrer, transformer et agréger
 * Les opérations essentielles :
 * - Opérations intermédiaires : filter, map, flatMap, distinct, sorted, limit, skip, peek
 * - Opérations terminales : toList, collect, forEach, reduce, count, min, max, findFirst, anyMatch, allMatch, noneMatch
 *
 * Une opération intermédiaire est généralement paresseuse : elle ne traite les éléments que lorsqu'une opération terminale déclenche l'exécution du pipeline.
 */
public class StreamExamples {

    record Employe(String nom, String service, int salaire) {}

    public static void demo() {
        List<Employe> employes = List.of(
                new Employe("Awa", "IT", 45000),
                new Employe("Moussa", "RH", 35000),
                new Employe("Fatou", "IT", 50000),
                new Employe("Ali", "RH", 40000)
        );

        // 1. filter + map + sorted + toList
        List<String> noms = employes.stream()
                .filter(e -> e.salaire() >= 40000)
                .map(Employe::nom)
                .sorted()
                .toList();

        System.out.println(noms); // [Ali, Awa, Fatou]

        // 2. reduce : calculer une somme
        int total = employes.stream()
                .map(Employe::salaire)
                .reduce(0, Integer::sum);

        System.out.println(total); // 170000

        // 3. count : compter les éléments
        long nombreIT = employes.stream()
                .filter(e -> e.service().equals("IT"))
                .count();

        System.out.println(nombreIT); // 2

        // 4. anyMatch / allMatch
        boolean salaireEleve = employes.stream()
                .anyMatch(e -> e.salaire() > 45000);

        System.out.println(salaireEleve); // true

        // 5. max : trouver le mieux payé
        employes.stream()
                .max((a, b) -> Integer.compare(a.salaire(), b.salaire()))
                .ifPresent(System.out::println);

        // 6. groupingBy : regrouper par service
        Map<String, List<Employe>> parService = employes.stream()
                .collect(Collectors.groupingBy(Employe::service));

        System.out.println(parService.keySet()); // [RH, IT] (ordre non garanti)
    }
}
