package com.example.springevents;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AnnotationDrivenEventListener {

    // Condition is written in Spring Expression Language (SpEL)
    @EventListener(condition = "#event.success")
    public void handleSuccessful(GenericSpringEvent<String> event) {
        System.out.println("Handling generic event (conditional).");
    }

    @TransactionalEventListener
    public void handleCustom(CustomSpringEvent event) {
        System.out.println("Handling event inside a transaction AFTER COMMIT");
    }
}
