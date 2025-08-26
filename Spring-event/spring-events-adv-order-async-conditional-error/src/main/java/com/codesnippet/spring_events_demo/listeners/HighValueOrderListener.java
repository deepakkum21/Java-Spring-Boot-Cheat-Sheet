package com.codesnippet.spring_events_demo.listeners;

import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// Conditional Listener
@Component
public class HighValueOrderListener {

    @EventListener(condition = "#event.amount >1000")
    public void handleHighValueOrder(OrderCreatedEvent event) {
        System.out.println(" High value order detected! OrderId: "
                           + event.getOrderId() + " Amount: " + event.getAmount());
    throw new RuntimeException("Exception");
    }
}
