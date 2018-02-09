package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroService {

	Collection<Hero> searchHeroesByName(String name);

	Hero findHeroByName(String name);

	Page<Hero> findAllHeroes(Pageable pageable);

	void addHero(Hero hero);

}
