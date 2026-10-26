package com.molo.p8designpatterns.dp2structure.decorator;

/**
 * Objectif : ajouter dynamiquement des fonctionnalités à un objet sans modifier sa classe et sans multiplier les sous-classes.
 * À retenir :
 * - Cafe : contrat commun à l'objet et à ses décorateurs.
 * - CafeSimple : objet de base.
 * - CafeDecorator : classe abstraite qui encapsule un Cafe.
 * - CafeAvecLait : ajoute un comportement et un coût supplémentaire.
 * - On peut empiler les décorateurs : new CafeAvecLait(new CafeAvecLait(new CafeSimple())).
 * Le Decorator privilégie la composition plutôt que la création d'une classe pour chaque combinaison possible.
 */
public class CafeAvecLait extends CafeDecorator {

    public CafeAvecLait(Cafe cafe) {
        super(cafe);
    }

    @Override
    public String description() {
        return cafe.description() + " + lait";
    }

    @Override
    public double prix() {
        return cafe.prix() + 0.50;
    }

    public static void main(String[] args) {
        Cafe cafe = new CafeSimple();
        cafe = new CafeAvecLait(cafe);

        System.out.println(cafe.description());
        System.out.println("Prix : " + cafe.prix() + " €");
    }
}
