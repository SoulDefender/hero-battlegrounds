package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.service.HeroDataReadServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
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
