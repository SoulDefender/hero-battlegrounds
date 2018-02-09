package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroDataController
{
	
	@RequestMapping(value = "/hero/{name}", method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	Hero findHeroByName(@RequestParam String name);

	@RequestMapping(value = "/heroes/{name}", method = RequestMethod.GET,
			produces = APPLICATION_JSON_VALUE)
	Collection<Hero> findHeroesByName(@RequestParam String name);

	@RequestMapping(value = "/heroes", method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	Collection<Hero> findAllHeroes(
		@RequestParam(required = false, defaultValue = "0") int page,
		@RequestParam(required = false, defaultValue = "10") int limit);
	
	@RequestMapping(value = "heroes", method = RequestMethod.POST,
		consumes = APPLICATION_JSON_VALUE)
	void addHero(Hero hero);
}
