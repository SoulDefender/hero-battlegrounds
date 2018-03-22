package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.web.service.HeroService;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
    public Hero findHeroByName(@PathVariable("name") String name) {
		return heroService.findHeroByName(name);
	}

	@Override
    public Collection<Hero> findHeroesByName(@PathVariable("name") String name) {
		return heroService.searchHeroesByName(name);
	}

	@Override
    public Collection<Hero> findAllHeroes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
		return heroService.findAllHeroes(new PageRequest(page, limit)).getContent();
	}

	@Override
	public void addHero(@RequestBody @Validated Hero hero) {
		heroService.addHero(hero);
	}
}
