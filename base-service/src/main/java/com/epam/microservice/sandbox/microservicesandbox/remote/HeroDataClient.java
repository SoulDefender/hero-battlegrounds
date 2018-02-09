package com.epam.microservice.sandbox.microservicesandbox.remote;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.remote.exceptions.HeroAddException;
import com.epam.microservice.sandbox.microservicesandbox.web.HeroDataController;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.Collection;
import java.util.Collections;

@FeignClient(
        name = "hero-service",
        url = "http://localhost:8093",
        fallback = HeroDataClient.HeroDataClientFallback.class
)
public interface HeroDataClient extends HeroDataController {

    class HeroDataClientFallback implements HeroDataClient {

        @Override
        public Hero findHeroByName(String name) {
            return null;
        }

        @Override
        public Collection<Hero> findHeroesByName(String name) {
            return Collections.emptyList();
        }

        @Override
        public Collection<Hero> findAllHeroes(int page, int limit) {
            return Collections.emptyList();
        }

        @Override
        public void addHero(Hero hero) {
            throw new HeroAddException();
        }
    }
}
