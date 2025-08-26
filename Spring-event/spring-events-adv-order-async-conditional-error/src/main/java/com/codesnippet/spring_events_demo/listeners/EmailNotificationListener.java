package com.codesnippet.spring_events_demo.listeners;

import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {

    @EventListener
    @Async
    @Order(2)
    public void handleOrderCreatedAndSendEmail(OrderCreatedEvent event) throws InterruptedException {
        System.out.println("Sending email for Order: "+event.getOrderId());
        Thread.sleep(3000);
        System.out.println("Email Sent for Order: "+event.getOrderId());

    }
}
