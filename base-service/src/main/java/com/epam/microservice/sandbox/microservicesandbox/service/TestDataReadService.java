package com.epam.microservice.sandbox.microservicesandbox.service;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
public interface TestDataReadService
{
    Collection<TestData> fetchAllTestData();
}
