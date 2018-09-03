package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import io.maksutov.heroes.battlegrounds.service.BattleGroundsService;
import io.maksutov.heroes.battlegrounds.service.HeroDataReadServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private final HeroDataReadServiceImpl readService;
    private final BattleGroundsService battleGroundsService;


    public HeroController(HeroDataReadServiceImpl readService,
            BattleGroundsService battleGroundsService) {
        this.readService = readService;
        this.battleGroundsService = battleGroundsService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Hero> heroData() {
        return readService.fetchAllHeroes();
    }


    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public FightResult sparringHero(@RequestBody @Valid OpponentHeroes opponents) {

        return battleGroundsService.sparringHeroes(opponents);
    }
}
