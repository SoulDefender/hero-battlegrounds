package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroDataController
{
	
	@RequestMapping(value = "/hero/{name}", method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	Hero findHeroByName(@PathVariable("name") String name);

	@RequestMapping(value = "/heroes/{name}", method = RequestMethod.GET,
			produces = APPLICATION_JSON_VALUE)
	Collection<Hero> findHeroesByName(@PathVariable("name") String name);

	@RequestMapping(value = "/heroes", method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	Collection<Hero> findAllHeroes(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit);
	
	@RequestMapping(value = "heroes", method = RequestMethod.POST,
		consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	void addHero(@RequestBody Hero hero);
}
