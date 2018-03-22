package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/battlegrounds")
public interface BattleController {

    @RequestMapping(value = "sparring", method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    FightResult sparring(OpponentHeroes opponentHeroes);

}
