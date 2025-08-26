package com.codesnippet.spring_events_demo.services;


import com.codesnippet.spring_events_demo.events.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(String orderId , double amount) {
        // Order creation logic
        System.out.println("Order created: " + orderId);
        publisher.publishEvent(new OrderCreatedEvent(orderId,amount));
    }
}
