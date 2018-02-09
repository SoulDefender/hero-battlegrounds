package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import java.util.Collection;
import java.util.List;

import com.epam.microservice.sandbox.microservicesandbox.model.HeroListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.epam.microservice.sandbox.microservicesandbox.store.StoreConstants.HEROES_COLLECTION;

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
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.skip((long)pageable.getOffset()),
				Aggregation.limit(pageable.getPageSize())
		);

		List<Hero> heroList = this.mongoTemplate
				.aggregate(aggregation, "heroes", Hero.class).getMappedResults();
		return new PageImpl<>(heroList, pageable, heroList.size());
	}

	@Override
	public void addHero(Hero hero) {
		this.mongoTemplate.save(hero, HEROES_COLLECTION);
	}
}
