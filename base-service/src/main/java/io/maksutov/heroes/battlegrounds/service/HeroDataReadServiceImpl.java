package io.maksutov.heroes.battlegrounds.service;

import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.remote.HeroDataClient;
import org.springframework.stereotype.Service;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
@Service
public class HeroDataReadServiceImpl implements HeroDataReadService
{
    private final HeroDataClient heroDataClient;

    public HeroDataReadServiceImpl(HeroDataClient heroDataClient) {
        this.heroDataClient = heroDataClient;
    }
    
    public Collection<Hero> fetchAllHeroes() {
        return heroDataClient.findAllHeroes(0, Integer.MAX_VALUE);
    }

    @Override
    public Collection<Hero> fetchHeroes(int page, int limit) {
        return heroDataClient.findAllHeroes(page, limit);
    }
}