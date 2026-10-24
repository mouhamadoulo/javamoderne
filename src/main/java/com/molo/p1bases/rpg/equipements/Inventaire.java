package com.molo.p1bases.rpg.equipements;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ENCAPSULATION : la liste interne est privée et n'est JAMAIS exposée telle quelle.
 * L'extérieur passe obligatoirement par les méthodes, qui font respecter les règles
 * (capacité maximale, pas de null...).
 */
public class Inventaire {

    private static final int CAPACITE_MAX = 5;

    private final List<Potion> potions = new ArrayList<>();

    public boolean ajouter(Potion potion) {
        if (potion == null || potions.size() >= CAPACITE_MAX) {
            return false;
        }
        potions.add(potion);
        return true;
    }

    /** Retire et renvoie la première potion, ou null si l'inventaire est vide. */
    public Potion retirerPotion() {
        return potions.isEmpty() ? null : potions.removeFirst(); // removeFirst : Java 21+ (SequencedCollection)
    }

    public boolean estVide() {
        return potions.isEmpty();
    }

    public int taille() {
        return potions.size();
    }

    /**
     * Vue en lecture seule : un appelant qui ferait getPotions().clear()
     * obtiendrait une UnsupportedOperationException au lieu de vider notre inventaire.
     */
    public List<Potion> getPotions() {
        return Collections.unmodifiableList(potions);
    }

    @Override
    public String toString() {
        return potions.isEmpty() ? "vide" : potions.toString();
    }
}

