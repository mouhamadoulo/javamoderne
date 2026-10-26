package com.molo.p8designpatterns.dp3comportement.observer;

/**
 * Observer — notifier automatiquement plusieurs objets
 * Définir une relation un-à-plusieurs : lorsqu'un objet change d'état, ses abonnés sont automatiquement notifiés.
 * Exemple concret : une agence de presse publie une actualité et tous ses abonnés la reçoivent.
 * À retenir :
 * - subscribe() : ajouter un observateur.
 * - unsubscribe() : retirer un observateur.
 * - publish() : notifier tous les observateurs.
 * - La liste est privée : les abonnés ne peuvent pas la modifier directement.
 * Quand utiliser Observer ?
 * - Notifications et événements métier.
 * - Interfaces graphiques et événements utilisateur.
 * - Systèmes de monitoring.
 * - Architecture événementielle, lorsque plusieurs composants doivent réagir à un changement.
 * Limites et bonnes pratiques : attention aux abonnements oubliés, aux notifications coûteuses et aux modifications concurrentes de la liste. Cet exemple est synchrone : les observateurs sont appelés dans le thread qui exécute publish().
 */
public class MainObserver {

    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        NewsSubscriber alice =
                news -> System.out.println("Alice reçoit : " + news);

        NewsSubscriber bob =
                news -> System.out.println("Bob reçoit : " + news);

        agency.subscribe(alice);
        agency.subscribe(bob);

        agency.publish("Java 25 est disponible !");

        agency.unsubscribe(bob);
        agency.publish("Nouvelle actualité !");
    }
}
