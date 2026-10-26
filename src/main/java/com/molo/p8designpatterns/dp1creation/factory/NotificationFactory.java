package com.molo.p8designpatterns.dp1creation.factory;

/**
 * Pattern Factory Method — déléguer la création
 * Cas concret : envoyer des notifications par e-mail ou SMS sans que le code client connaisse leur implémentation concrète.
 * À retenir :
 * - La fabrique abstraite définit la méthode de création.
 * - Une implémentation concrète décide quel objet créer.
 * - On peut ajouter SmsNotification et une fabrique dédiée sans modifier la logique métier de notifier().
 */
public abstract class NotificationFactory {

    public abstract Notification creerNotification();

    public void notifier(String message) {
        Notification notification = creerNotification();
        notification.envoyer(message);
    }
}