package com.molo.p8designpatterns.dp1creation.factory;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory() {
            @Override
            public Notification creerNotification() {
                return new EmailNotification();
            }
        };

        factory.notifier("Votre paiement est confirmé.");
    }
}