package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.TestDataCollection;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Dmytro_Maksutov
 */
public interface TestDataController
{
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    TestDataCollection getAllTestData();
}
