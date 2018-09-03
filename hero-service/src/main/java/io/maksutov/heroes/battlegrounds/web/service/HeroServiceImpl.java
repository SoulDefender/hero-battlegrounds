package io.maksutov.heroes.battlegrounds.web.service;

import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBObject;
import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.store.StoreConstants;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.maksutov.heroes.battlegrounds.store.StoreConstants.HEROES_COLLECTION;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;


/**
 * @author Dmytro_Maksutov
 */
@Service
public class HeroServiceImpl implements HeroService {

	private final MongoTemplate mongoTemplate;

	public HeroServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Collection<Hero> searchHeroesByName(String name) {
		return this.mongoTemplate.find(
				Query.query(Criteria.where("name").regex(String.format("%s.*", name))),
				Hero.class
		);
	}

	@Override
	public Hero findHeroByName(String name) {
		return this.mongoTemplate.findOne(
				Query.query(Criteria.where("name").is(name)),
				Hero.class);
	}

	@Override
	public Page<Hero> findAllHeroes(Pageable pageable) {

		List<Hero> heroList = mongoTemplate
				.find(new Query().with(pageable), Hero.class, HEROES_COLLECTION);

		return new PageImpl<>(heroList, pageable, heroList.size());
	}

	@Override
	public void addHero(Hero hero) {

		this.mongoTemplate.save(hero, HEROES_COLLECTION);
	}
}
