package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.service.HeroDataReadServiceImpl;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

/**
 * @author Dmytro_Maksutov
 */
@RestController
@RequestMapping("/")
public class HeroController
{

    private final HeroDataReadServiceImpl readService;

    public HeroController(HeroDataReadServiceImpl readService) {
        this.readService = readService;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Collection<Hero> heroData() {
        return readService.fetchAllHeroes();
    }
}
