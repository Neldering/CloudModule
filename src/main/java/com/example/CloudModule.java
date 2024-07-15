package com.example;

import com.example.configserver.ConfigServerApplication;
import com.example.application.ApplicationModuleApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApplication {

    public static void main(String[] args) {
        // Start the Config Server
        ConfigurableApplicationContext configServerContext =
                new SpringApplicationBuilder(ConfigServer.class)
                        .profiles("config-server")
                        .run(args);

        // Start the Application Module after the Config Server is up
        ConfigurableApplicationContext clientAppContext =
                new SpringApplicationBuilder(ConfigClient.class)
                        .parent(configServerContext)
                        .profiles("client-app")
                        .run(args);
    }