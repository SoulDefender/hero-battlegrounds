package com.epam.microservice.sandbox.microservicesandbox.service;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import com.epam.microservice.sandbox.microservicesandbox.model.TestDataCollection;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmytro_Maksutov
 */
@Service
public class TestDataReadService
{

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public TestDataReadService(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }
    
    public Collection<TestData> fetchAllTestData() {
        List<ServiceInstance> serviceInstances =
            this.discoveryClient.getInstances("dataprovider-service");
        return serviceInstances.stream().findFirst().map(si -> 
            restTemplate.getForEntity(si.getUri(), TestDataCollection.class).getBody()).orElse(new TestDataCollection(
                Collections.emptyList())).getTestDataCollection();
    }
    
}
