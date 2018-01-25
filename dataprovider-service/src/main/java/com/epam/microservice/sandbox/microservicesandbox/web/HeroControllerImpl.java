package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.web.service.HeroService;
import java.util.Collection;
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
