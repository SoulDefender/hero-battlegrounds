package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import java.util.Collection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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
	public Hero findHeroByName(String name) {
		return null;
	}

	@Override
	public Collection<Hero> findAllHeroes(int page, int limit) {
		return null;
	}

	@Override
	public void addHero(Hero hero) {

	}
}
