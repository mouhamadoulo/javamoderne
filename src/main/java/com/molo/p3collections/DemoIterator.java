package com.molo.p3collections;

import java.util.*;

/**
 * Parcourir et Supprimer
 * À retenir : Iterator permet un parcours et une suppression contrôlée ;
 * ListIterator ajoute notamment le parcours inverse, le remplacement et l'insertion dans une liste.
 */
public class DemoIterator {
    public static void main(String[] args) {
        List<Integer> nombres =
                new ArrayList<>(List.of(1, 2, 3, 4, 5));

        Iterator<Integer> it = nombres.iterator();

        while (it.hasNext()) {
            Integer n = it.next();

            if (n % 2 == 0) {
                it.remove(); // Suppression sécurisée
            }
        }

        System.out.println(nombres); // [1, 3, 5]

        // ListIterator : parcours bidirectionnel
        ListIterator<Integer> li = nombres.listIterator();

        while (li.hasNext()) {
            System.out.println(li.next());
        }

        while (li.hasPrevious()) {
            System.out.println(li.previous());
        }
    }
}

