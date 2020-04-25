package org.chimerax.demeter;

import jdk.internal.module.IllegalAccessLogger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemeterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemeterApplication.class, args);
    }

}
