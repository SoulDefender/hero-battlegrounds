package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroService {

	Hero findHeroByName(String name);

	Collection<Hero> findAllHeroes(int page, int limit);

	void addHero(Hero hero);

}
