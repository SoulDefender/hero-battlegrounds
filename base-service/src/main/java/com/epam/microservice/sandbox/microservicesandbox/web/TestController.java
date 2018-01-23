package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import com.epam.microservice.sandbox.microservicesandbox.service.TestDataReadServiceImpl;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
@RestController
@RequestMapping("/")
public class TestController
{

    private final TestDataReadServiceImpl readService;

    public TestController(TestDataReadServiceImpl readService) {
        this.readService = readService;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Collection<TestData> testData() {
        return readService.fetchAllTestData();
    }
}
