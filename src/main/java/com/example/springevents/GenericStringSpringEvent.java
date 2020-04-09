package com.example.springevents;

// This must be created for the generic publisher due to type erasure
public class GenericStringSpringEvent extends GenericSpringEvent<String> {

    public GenericStringSpringEvent(String what, boolean success) {
        super(what, success);
    }
}
