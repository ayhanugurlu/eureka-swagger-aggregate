package com.mergenie.apiaggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Discovery Application start point, please do not add functional code to this class.
 */
@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
public class DiscoveryApplication {

    /**
     * Entry point for Discovery Spring Boot Application.
     *
     * @param args command line parameters
     */
    public static void main(final String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }
}


