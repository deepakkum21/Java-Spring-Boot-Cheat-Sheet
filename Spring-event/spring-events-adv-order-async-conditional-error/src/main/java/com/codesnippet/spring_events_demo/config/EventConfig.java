package com.codesnippet.spring_events_demo.config;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Configuration
public class EventConfig {
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(){
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster
                = new SimpleApplicationEventMulticaster();

        simpleApplicationEventMulticaster.setErrorHandler(error->{
            System.out.println("Error in event listener: " + error.getMessage());
        });

        return simpleApplicationEventMulticaster;
    }
}
