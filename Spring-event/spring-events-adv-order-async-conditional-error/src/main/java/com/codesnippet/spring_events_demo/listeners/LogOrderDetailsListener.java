package com.codesnippet.spring_events_demo.listeners;

import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogOrderDetailsListener {

    @EventListener
    @Async
    @Order(1)
    public void logOrderCreated(OrderCreatedEvent event) throws InterruptedException {
        System.out.println("Order Placed For Order Id: " + event.getOrderId());
        Thread.sleep(1000);
        System.out.println("logOrderCreated"+event.getOrderId());
    }
}
