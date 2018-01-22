package com.epam.microservice.sandbox.microservicesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dmytro_Maksutov
 */
@Data
@AllArgsConstructor
public class TestData
{
    private String id;
    
    private String name;
    
    private String surname;
}
