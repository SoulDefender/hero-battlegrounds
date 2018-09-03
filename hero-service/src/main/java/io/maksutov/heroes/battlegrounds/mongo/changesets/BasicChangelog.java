package io.maksutov.heroes.battlegrounds.mongo.changesets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.store.StoreConstants;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.mongodb.core.BulkOperations.BulkMode.UNORDERED;


@ChangeLog(order = "001")
@Profile({ "!test" })
public class BasicChangelog {

    @ChangeSet(order = "001", id = "importHeroDumpToMongo", author = "maksutov.dmitry@gmail.com")
    public void importHeroDumpToMongo(MongoTemplate mongoTemplate) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        BulkOperations ops = mongoTemplate.bulkOps(UNORDERED, StoreConstants.HEROES_COLLECTION);
        Resource resource = new ClassPathResource("/heroes_dump.json");
        List<Hero> heroes = mapper.readValue(resource.getInputStream(), new TypeReference<List<Hero>>() {

        });
        ops.insert(heroes);
        ops.execute();
    }

}
