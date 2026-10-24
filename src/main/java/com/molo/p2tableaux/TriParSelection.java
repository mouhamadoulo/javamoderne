package com.molo.p2tableaux;

import java.util.Arrays;

/**
 * Méthode de Tri : On sélectionne le minimum et on le place devant
 * - On parcourt la partie non triée pour trouver son minimum
 * - Avantages : Au plus n-1 échanges, En place, code très court
 * - Inconvénients : O(n²) même déjà trié, Non stable
 */
public class TriParSelection {

    public static void main(String[] args) {

        int[] montableau = {6,3,9,1,7,2,5};

        System.out.println("----------------TRI PAR SELECTION-----------------");
        System.out.println("TABLEAU AVANT TRI : "+Arrays.toString(montableau));

        //Algo de Tri par sélection
        int n = montableau.length;
        for(int i = 0; i < n - 1; i++){
            int min = i;
            for(int j = i + 1; j < n; j++){
                if(montableau[j] < montableau[min]){
                    min = j;
                }
            }
            swapvaleurs(montableau,i,min);
        }

        System.out.println("TABLEAU APRES TRI : "+Arrays.toString(montableau));

    }

    static void swapvaleurs(int[] t,int i,int j){
        int tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
}
