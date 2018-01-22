package com.epam.microservice.sandbox.microservicesandbox.config;

import java.io.IOException;

import com.epam.microservice.sandbox.microservicesandbox.model.TestData;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.Arrays;

/**
 * @author Dmytro_Maksutov
 */
@Configuration
@RefreshScope
public class MongoConfig
{
    @Value("${embeded.mongo.url}")
    private String mongoDbUrl;
    
    @Value("${embeded.mongo.db.name}")
    private String mongoDbName;
    
    
    @Bean
    MongoTemplate mongoTemplate()throws IOException
    {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoDbUrl);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDbName);
        mongoTemplate.insertAll(Arrays.asList(
            new TestData("1", "number_one", "dr"),
            new TestData("2", "number_two", "mr"),
            new TestData("3", "number_three", "ms"),
            new TestData("4", "number_four", "sir")
        ));
        return mongoTemplate;
    }
}
