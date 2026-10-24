package com.molo.p2tableaux;

import java.util.Arrays;

/**
 * Méthode de Tri : Les plus grands élements remontent comme des bulles
 * - On compare chaque paire de voisins et on les échange s'ils sont mal ordonnés
 * - Avantages : Très simple à écrire, Stable, O(n) si déjà trié
 * - Inconvénients : O(n²) comparaisons et échanges, Inutilisable sur de gros volumes
 */
public class TriBulles {

    public static void main(String[] args) {

        int[] montableau = {5,1,4,2,8,3,6};

        System.out.println("----------------TRI A BULLES-----------------");
        System.out.println("TABLEAU AVANT TRI : "+Arrays.toString(montableau));

        //Algo de Tri à Bulles
        int n = montableau.length;
        for(int i = 0; i < n - 1; i++){
            boolean echangedone = false;
            for(int j = 0; j < n - 1 - i; j++){
                if(montableau[j] > montableau[j+1]){
                    swapvaleurs(montableau,j,j+1);
                    echangedone = true;
                }
            }
            if(!echangedone) {
                break; //tout est trié
            }
        }

        System.out.println("TABLEAU APRES TRI : "+Arrays.toString(montableau));

    }

    static void swapvaleurs(int[] t,int i,int j){
        int tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
}
