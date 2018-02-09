package com.epam.microservice.sandbox.microservicesandbox;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Dmytro_Maksutov
 */
@Configuration
@Profile("test")
public class TestConfig {
	@Bean
	MongoTemplate testMongoTemplate()throws IOException
	{
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp("localhost");
		MongoClient mongoClient = mongo.getObject();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test");
		mongoTemplate.insertAll(Arrays.asList(
			new TestData("1", "number_one", "dr"),
			new TestData("2", "number_two", "mr"),
			new TestData("3", "number_three", "ms"),
			new TestData("4", "number_four", "sir")
		));
		return mongoTemplate;
	}
}
