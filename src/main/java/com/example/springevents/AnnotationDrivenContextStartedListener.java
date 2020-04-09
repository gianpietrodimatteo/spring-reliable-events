package com.example.springevents;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

// You need to enableAsync in the application (declare async if you want to hadle it asynchonously)
// Any public method of a managed bean can be an event listener
public class AnnotationDrivenContextStartedListener {
    @Async
    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        System.out.println("Handling context started event.");
    }
}
