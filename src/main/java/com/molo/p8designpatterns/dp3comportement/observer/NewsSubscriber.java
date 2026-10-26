package com.molo.p8designpatterns.dp3comportement.observer;

/**
 * Interface représentant un observateur.
 * @FunctionalInterface permet aussi d'utiliser une lambda.
 */
@FunctionalInterface
public interface NewsSubscriber {
    void receiveNews(String news);
}
