package io.maksutov.heroes.battlegrounds.service;

import io.maksutov.heroes.battlegrounds.model.Hero;

import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroDataReadService
{
    Collection<Hero> fetchAllHeroes();

    Collection<Hero> fetchHeroes(int page, int limit);
}
