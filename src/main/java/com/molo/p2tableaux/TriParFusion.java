package com.molo.p2tableaux;

import java.util.Arrays;

/**
 * Méthode de Tri : Diviser pour régner, Diviser le tableau récursivement en deux, Trier chaque moitié
 * - Fusionner les deux moitiés triées.
 * - Avantages : Complexité en O(n log n), Tri stable
 * - Inconvénients : Nécessite un tableau temporaire supplémentaire
 */
public class TriParFusion {

    public static void main(String[] args) {

        int[] montableau = {6,2,7,4,1,8,3,5};

        System.out.println("TABLEAU AVANT TRI : "+Arrays.toString(montableau));

        // Trier tout le tableau, du premier au dernier indice
        triFusion(montableau, 0, montableau.length - 1);

        System.out.println("TABLEAU APRES TRI : "+Arrays.toString(montableau));

    }

    //Algo de Tri par fusion
    static void triFusion(int[] t, int g, int d){
        if(g >= d) return;
        int m = (g + d) / 2;
        triFusion(t, g, m); //gauche
        triFusion(t,m + 1, d); //droite
        fusion(t, g, m, d);
    }
    static void fusion(int[] t, int g, int m, int d){
        int[] tmp = new int[d - g + 1];
        int i = g, j = m + 1, k = 0;
        while (i <= m && j <= d){
            tmp[k++] = t[i] <= t[j] ? t[i++] : t[j++];
        }
        while (i <= m){
            tmp[k++] = t[i++];
        }
        while (j <= d){
            tmp[k++] = t[j++];
        }
        System.arraycopy(tmp,0,t,g,k);
    }
}
