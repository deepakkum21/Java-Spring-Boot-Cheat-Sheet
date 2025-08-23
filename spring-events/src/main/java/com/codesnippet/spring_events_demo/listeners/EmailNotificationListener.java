package com.codesnippet.spring_events_demo.listeners;

import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {

    @EventListener
    public void handleOrderCreatedAndSendEmail(OrderCreatedEvent event){
        System.out.println("Sending email for Order: "+event.getOrderId());
    }
}
