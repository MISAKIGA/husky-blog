package com.misakiga.husky.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProfileBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ProfileBootstrap.class,args);
    }
}
