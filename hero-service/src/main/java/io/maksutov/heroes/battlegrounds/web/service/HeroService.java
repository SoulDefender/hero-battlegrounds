package io.maksutov.heroes.battlegrounds.web.service;

import io.maksutov.heroes.battlegrounds.model.Hero;
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
