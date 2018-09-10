package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import io.maksutov.heroes.battlegrounds.service.BattleGroundsService;
import io.maksutov.heroes.battlegrounds.service.HeroesDataServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @author Dmytro_Maksutov
 */
@RestController
@RequestMapping("/")
public class HeroController
{

    private final HeroesDataServiceImpl heroesService;
    private final BattleGroundsService battleGroundsService;


    public HeroController(HeroesDataServiceImpl heroesService,
            BattleGroundsService battleGroundsService) {

        this.heroesService = heroesService;
        this.battleGroundsService = battleGroundsService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Hero> heroData() {

        return heroesService.fetchAllHeroes();
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Hero addHero(@RequestBody @Validated Hero hero) {

        return heroesService.addHero(hero);
    }


    @RequestMapping(value = "/fight", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public FightResult sparringHero(@RequestBody @Valid OpponentHeroes opponents) {

        return battleGroundsService.sparringHeroes(opponents);
    }
}
