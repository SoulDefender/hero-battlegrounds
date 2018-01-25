package com.epam.microservice.sandbox.microservicesandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DataproviderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DataproviderApplication.class, args);
    }
}
