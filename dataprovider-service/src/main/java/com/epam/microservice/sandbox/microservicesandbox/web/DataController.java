package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import com.epam.microservice.sandbox.microservicesandbox.model.TestDataCollection;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro_Maksutov
 */
@RequestMapping("/")
@RestController
public class DataController implements TestDataController
{

    private final MongoTemplate mongoTemplate;

    public DataController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TestDataCollection getAllTestData() {
        return new TestDataCollection(mongoTemplate.findAll(TestData.class));
    }
    
}
