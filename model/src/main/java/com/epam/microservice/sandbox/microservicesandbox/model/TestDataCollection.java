package com.epam.microservice.sandbox.microservicesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
@Data
@AllArgsConstructor
public class TestDataCollection
{
    private Collection<TestData> testDataCollection;
}
