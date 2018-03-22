package io.maksutov.heroes.battlegrounds.remote.config;

import com.github.mongobee.Mongobee;
import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Dmytro_Maksutov
 */
@Configuration
@RefreshScope
@Profile("!test")
public class MongoConfig
{
    @Value("${embeded.mongo.url}")
    private String mongoDbUrl;
    
    @Value("${embeded.mongo.db.name}")
    private String mongoDbName;
    
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoClientFactoryBean clientFactoryBean = mongoClientFactory();
        Mongo mongo = clientFactoryBean.getObject();
        return new MongoTemplate(mongo, mongoDbName);
    }

    @Bean
    MongoClientFactoryBean mongoClientFactory() {
        MongoClientFactoryBean clientFactoryBean = new MongoClientFactoryBean();
        clientFactoryBean.setHost(mongoDbUrl.split(":")[0]);
        clientFactoryBean.setPort(Integer.parseInt(
            mongoDbUrl.split(":")[1]));
        clientFactoryBean.setMongoClientOptions(MongoClientOptions.builder()
        .maxConnectionIdleTime(10000)
        .minConnectionsPerHost(20)
        .build());
        return clientFactoryBean;
    }


    @Bean
    public Mongobee mongobee(Environment environment) {

        Mongobee runner = new Mongobee(String.format("mongodb://%s/%s", mongoDbUrl, mongoDbName));
        runner.setDbName(mongoDbName);         // host must be set if not set in URI
        runner.setChangeLogsScanPackage(
                "io.maksutov.heroes.battlegrounds.mongo.changesets");
        // the package to be scanned for changesets

        runner.setSpringEnvironment(environment);
        return runner;
    }
}
