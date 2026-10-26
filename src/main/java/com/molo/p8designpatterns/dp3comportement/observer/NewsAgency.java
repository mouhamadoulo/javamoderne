package com.molo.p8designpatterns.dp3comportement.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {

    private final List<NewsSubscriber> subscribers = new ArrayList<>();

    public void subscribe(NewsSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(NewsSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(String news) {
        subscribers.forEach(s -> s.receiveNews(news));
    }
}
