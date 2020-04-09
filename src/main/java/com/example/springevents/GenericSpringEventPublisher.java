package com.example.springevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class GenericSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishEvent(final String message) {
        System.out.println("Publishing generic event. ");
        GenericStringSpringEvent customSpringEvent = new GenericStringSpringEvent(message, true);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}


