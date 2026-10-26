package com.molo.p8designpatterns.dp2structure.adapter;

/**
 * Objectif : faire collaborer deux interfaces incompatibles sans modifier la classe existante.
 * À retenir :
 * - PriseUsb : interface attendue par le client.
 * - AnciennePrise : classe existante incompatible.
 * - AdaptateurUsb : fait le lien entre les deux interfaces.
 * - L'adaptateur utilise la composition : il contient une instance de AnciennePrise.
 */
public class AdaptateurUsb implements PriseUsb {

    private final AnciennePrise anciennePrise;

    public AdaptateurUsb(AnciennePrise anciennePrise) {
        this.anciennePrise = anciennePrise;
    }

    @Override
    public void connecterUsb() {
        anciennePrise.brancherAnciennePrise();
    }

    public static void main(String[] args) {
        PriseUsb prise = new AdaptateurUsb(new AnciennePrise());
        prise.connecterUsb();
    }
}