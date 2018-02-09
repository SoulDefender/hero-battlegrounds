package com.epam.microservice.sandbox.microservicesandbox.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;

import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
public interface HeroDataReadService
{
    Collection<Hero> fetchAllHeroes();

    Collection<Hero> fetchHeroes(int page, int limit);
}
