package com.molo.p1pilierspoo.rpg.personnages;

import com.molo.p1pilierspoo.rpg.equipements.Arme;
import com.molo.p1pilierspoo.rpg.equipements.Inventaire;
import com.molo.p1pilierspoo.rpg.equipements.Potion;

import java.util.Objects;
/**
 * ABSTRACTION (par classe abstraite) : "Personnage" est un concept, on ne peut pas
 * en créer un directement (new Personnage(...) ne compile pas). Elle regroupe ce qui
 * est commun et laisse aux sous-classes ce qui varie (calculerDegats).
 */
public abstract class Personnage {

    // ---------- ENCAPSULATION : état privé, modifiable uniquement via des méthodes ----------
    private final String nom;
    private final int pvMax;
    private int pv;

    // ---------- COMPOSITION / AGRÉGATION ----------
    // Agrégation : l'arme vient de l'extérieur, existe sans le personnage et peut être changée.
    private Arme arme;
    // Composition forte : l'inventaire est créé PAR le personnage et vit/meurt avec lui.
    private final Inventaire inventaire = new Inventaire();

    protected Personnage(String nom, int pvMax, Arme arme) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (pvMax <= 0) {
            throw new IllegalArgumentException("pvMax doit être positif");
        }
        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pvMax;
        this.arme = Objects.requireNonNull(arme, "Un personnage doit avoir une arme");
    }

    // ---------- Template Method : l'algorithme est fixé (final), une étape varie ----------
    public final void attaquer(Personnage cible) {
        if (!estVivant()) {
            System.out.println(nom + " est K.O. et ne peut pas attaquer.");
            return;
        }
        int degats = calculerDegats();           // ← appel POLYMORPHE : la version exécutée dépend du type réel
        System.out.printf("  %s attaque %s avec %s : %d dégâts%n",
                nom, cible.getNom(), arme.nom(), degats);
        cible.recevoirDegats(degats);            // ← polymorphe aussi (Guerrier la redéfinit)
    }

    /** Chaque classe concrète DOIT dire comment elle calcule ses dégâts. */
    protected abstract int calculerDegats();

    /** Comportement par défaut, que les sous-classes peuvent redéfinir (@Override). */
    public void recevoirDegats(int degats) {
        if (degats < 0) {
            throw new IllegalArgumentException("Dégâts négatifs interdits");
        }
        pv = Math.max(0, pv - degats);           // invariant : 0 <= pv <= pvMax
    }

    public void soigner(int montant) {
        if (montant < 0) {
            throw new IllegalArgumentException("Soin négatif interdit");
        }
        pv = Math.min(pvMax, pv + montant);
    }

    public boolean boirePotion() {
        Potion potion = inventaire.retirerPotion();
        if (potion == null) {
            return false;
        }
        soigner(potion.getSoin());
        System.out.printf("  %s boit %s → %d/%d PV%n", nom, potion, pv, pvMax);
        return true;
    }

    /** On change d'arme sans changer de classe : la force de la composition face à l'héritage. */
    public void equiper(Arme nouvelleArme) {
        this.arme = Objects.requireNonNull(nouvelleArme);
        System.out.println("  " + nom + " s'équipe de : " + nouvelleArme.description());
    }

    // ---------- Accesseurs : lecture seule pour l'extérieur ----------
    public String getNom()           { return nom; }
    public int getPv()               { return pv; }
    public int getPvMax()            { return pvMax; }
    public boolean estVivant()       { return pv > 0; }
    public Inventaire getInventaire() { return inventaire; }

    /** Accès réservé aux sous-classes (protected) : elles en ont besoin pour leurs calculs. */
    protected Arme getArme() {
        return arme;
    }

    protected boolean estEnDanger() {
        return pv < pvMax * 0.3;
    }

    @Override
    public String toString() {
        // getClass().getSimpleName() est lui-même polymorphe : "Guerrier", "Mage"...
        return String.format("%-8s %-10s %3d/%-3d PV | %s",
                getClass().getSimpleName(), nom, pv, pvMax, arme.description());
    }
}
