package com.molo.p8designpatterns.dp2structure.proxy;

/**
 * Objectif : contrôler l'accès à un objet réel, par exemple pour appliquer des autorisations, gérer le cache ou retarder le chargement d'une ressource.
 * Ici, le proxy charge un document uniquement lorsqu'on lui demande de l'afficher.
 * À retenir :
 * - Document : interface commune au proxy et à l'objet réel.
 * - DocumentReel : réalise le véritable travail.
 * - DocumentProxy : contrôle l'accès et retarde la création de l'objet réel.
 * - Le document n'est chargé qu'une seule fois dans cet exemple.
 */
public class DocumentProxy implements Document {

    private final String nom;
    private DocumentReel documentReel;

    public DocumentProxy(String nom) {
        this.nom = nom;
    }

    @Override
    public void afficher() {
        if (documentReel == null) {
            documentReel = new DocumentReel(nom);
        }

        documentReel.afficher();
    }

    public static void main(String[] args) {
        Document document = new DocumentProxy("confidentiel.pdf");

        System.out.println("Document créé, mais non chargé.");

        document.afficher();
        document.afficher();
    }
}