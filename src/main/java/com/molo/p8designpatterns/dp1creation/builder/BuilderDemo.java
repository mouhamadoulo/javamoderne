package com.molo.p8designpatterns.dp1creation.builder;

public class BuilderDemo {

    public static void main(String[] args) {
        User user = new User.Builder("Moussa", "moussa@example.com")
                .age(30)
                .build();

        System.out.println(user);
    }
}
