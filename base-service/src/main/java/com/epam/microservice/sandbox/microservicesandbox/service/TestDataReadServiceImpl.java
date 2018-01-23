package com.epam.microservice.sandbox.microservicesandbox.service;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import com.epam.microservice.sandbox.microservicesandbox.remote.TestDataClient;
import org.springframework.stereotype.Service;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
@Service
public class TestDataReadServiceImpl implements TestDataReadService
{
    private final TestDataClient testDataClient;

    public TestDataReadServiceImpl(TestDataClient testDataClient) {
        this.testDataClient = testDataClient;
    }
    
    public Collection<TestData> fetchAllTestData() {
        return testDataClient.getAllTestData().getTestDataCollection();
    }
}
