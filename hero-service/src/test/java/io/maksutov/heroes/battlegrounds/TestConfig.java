package io.maksutov.heroes.battlegrounds;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import java.io.IOException;
import java.util.Arrays;

import io.maksutov.heroes.battlegrounds.model.Hero;
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
        mongoTemplate.insert(Arrays.asList(
                new Hero("1", "James", "Mr", 18, 60,
                        180, "", null)
        ), "heroes");
		return mongoTemplate;
	}

}
