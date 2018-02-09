package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.web.service.HeroService;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro_Maksutov
 */
@RestController
public class HeroControllerImpl implements HeroDataController {

	private HeroService heroService;

	public HeroControllerImpl(HeroService heroService) {
		this.heroService = heroService;
	}

	@Override
	public Hero findHeroByName(String name) {
		return heroService.findHeroByName(name);
	}

	@Override
	public Collection<Hero> findHeroesByName(String name) {
		return heroService.searchHeroesByName(name);
	}

	@Override
	public Collection<Hero> findAllHeroes(int page, int limit) {
		return heroService.findAllHeroes(new PageRequest(page, limit)).getContent();
	}

	@Override
	public void addHero(@RequestBody @Validated Hero hero) {
		heroService.addHero(hero);
	}
}
