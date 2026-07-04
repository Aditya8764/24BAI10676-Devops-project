package com.example.college_event_web.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;
import io.micrometer.graphite.GraphiteConfig;
import io.micrometer.graphite.GraphiteMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GraphiteConfiguration {

    // Pulls the IP address from your application.properties, defaults to localhost
    @Value("${graphite.host:localhost}")
    private String graphiteHost;

    // Pulls the port from your application.properties, defaults to 2004
    @Value("${graphite.port:2004}")
    private int graphitePort;

    @Bean
    public GraphiteMeterRegistry graphiteMeterRegistry() {
        GraphiteConfig graphiteConfig = new GraphiteConfig() {
            @Override
            public String get(String key) {
                return null; // Return null to use default values for un-overridden properties
            }

            @Override
            public String host() {
                return graphiteHost;
            }

            @Override
            public int port() {
                return graphitePort;
            }

            @Override
            public Duration step() {
                // Determines how frequently metrics are pushed to Graphite
                return Duration.ofSeconds(60); 
            }
        };

        // Creates and returns the registry using standard hierarchical naming
        return new GraphiteMeterRegistry(
                graphiteConfig,
                Clock.SYSTEM,
                HierarchicalNameMapper.DEFAULT
        );
    }
}