package com.oreilly.beginner.config;

import com.oreilly.beginner.json.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // The individual methods to create and control Beans use @Bean
    @Bean
    public Greeting defaultGreeting() {
        return new Greeting("Hello World");
    }
    @Bean
    public Greeting yoManGreeting() {
        return new Greeting("Yo Man");
    }
}
