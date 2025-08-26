package com.codesnippet.spring_events_demo.listeners;

import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogOrderDetailsListener {
    @EventListener
    public void logOrderCreated(OrderCreatedEvent event) {
        System.out.println("Order Placed For Order Id: " + event.getOrderId());
    }
}
