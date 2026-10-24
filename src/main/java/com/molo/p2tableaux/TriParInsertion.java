package com.molo.p2tableaux;

import java.util.Arrays;

/**
 * Méthode de Tri : Chaque élément est inséré à sa place, comme des cartes en main
 * - Comme pour trier des cartes, on prend la suivante et on l'insère à sa place
 * - Avantages : Excellent sur petit tableau ou tableau déjà trié, Stable, En place
 * - Inconvénients : O(n²) sur des données aléatoires, Beaucoup de décalages si tableau inversé
 */
public class TriParInsertion {

    public static void main(String[] args) {

        int[] montableau = {4,7,2,6,1,5,3};

        System.out.println("----------------TRI PAR INSERTION-----------------");
        System.out.println("TABLEAU AVANT TRI : "+Arrays.toString(montableau));

        //Algo de Tri par insertion
        int n = montableau.length;
        for(int i = 1; i < n; i++){
            int cle = montableau[i];
            int j = i - 1;
            while(j >= 0 && montableau[j] > cle){
                montableau[j+1] = montableau[j]; // on décale
                j--;
            }
            montableau[j+1] = cle;
        }


        System.out.println("TABLEAU APRES TRI : "+Arrays.toString(montableau));

    }

}
