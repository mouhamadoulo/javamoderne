package com.molo.p2tableaux;

import java.util.Arrays;

/**
 * Méthode de Tri : Un pivot, une partition, et on recommence de chaque côté
 * - Comme
 * - Avantages : Le plus rapide en moyenne, O(log n) de pile
 * - Inconvénients : O(n²) si les pivots sont mauvais, Non stable
 */
public class TriRapide {

    public static void main(String[] args) {

        int[] montableau = {5,2,8,1,9,3,7,4};

        System.out.println("TABLEAU AVANT TRI : "+Arrays.toString(montableau));

        triRapide(montableau, 0, montableau.length - 1);

        System.out.println("TABLEAU APRES TRI : "+Arrays.toString(montableau));

    }

    //Algo de Tri rapide
    static void triRapide(int[] t, int g, int d){
        if(g >= d) return;
        int p = partition(t, g, d);
        triRapide(t, g, p - 1);
        triRapide(t, p + 1, d);
    }
    static int partition(int[] t, int g, int d){
        int pivot = t[d], i = g;
        for(int j = g; j < d; j++){
            if(t[j] < pivot){
                swapvaleurs(t, i++, j);
            }
        }
        swapvaleurs(t, i, d);
        return i;
    }
    static void swapvaleurs(int[] t,int i,int j){
        int tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
}
