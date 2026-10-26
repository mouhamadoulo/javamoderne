package com.molo.p8designpatterns.dp1creation.factory;

public class EmailNotification implements Notification {

    @Override
    public void envoyer(String message) {
        System.out.println("Email : " + message);
    }
}