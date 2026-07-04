package com.example.college_event_web;

import io.micrometer.statsd.StatsdConfig;
import io.micrometer.statsd.StatsdFlavor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomStatsdConfig {
    @Bean
    public StatsdConfig statsdConfig() {
        return new StatsdConfig() {
            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public String host() {
                return "graphite";
            }

            @Override
            public int port() {
                return 8125;
            }

            @Override
            public StatsdFlavor flavor() {
                return StatsdFlavor.ETSY;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }
        };
    }
}
