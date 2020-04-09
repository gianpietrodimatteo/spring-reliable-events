package com.example.springevents;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

// This implementation would require us to make GenericSpringEvent inherit from ApplicationEvent
@Component
//public class GenericSpringEventListener implements ApplicationListener<GenericSpringEvent<String>> {
public class GenericSpringEventListener {

    //    @Override
    public void onApplicationEvent(@NonNull GenericSpringEvent<String> event) {
        System.out.println("Received spring generic event - " + event.getWhat());
    }
}
