package com.molo.p8designpatterns.dp1creation.singleton;

public class SingletonDemo {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.afficher();
        System.out.println(s1 == s2); // true
    }
}
