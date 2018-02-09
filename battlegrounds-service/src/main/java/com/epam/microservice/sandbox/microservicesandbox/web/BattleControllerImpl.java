package com.epam.microservice.sandbox.microservicesandbox.web;

import com.epam.microservice.sandbox.microservicesandbox.model.FightResult;
import com.epam.microservice.sandbox.microservicesandbox.model.OpponentHeroes;
import com.epam.microservice.sandbox.microservicesandbox.web.service.BattlegroundService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Dmytro_Maksutov
 */
@RestController
public class BattleControllerImpl implements BattleController {

	private BattlegroundService battlegroundService;

	public BattleControllerImpl(BattlegroundService battlegroundService) {
		this.battlegroundService = battlegroundService;
	}

    @Override
    public FightResult sparring(@RequestBody @Valid OpponentHeroes opponentHeroes) {
        return battlegroundService.heroFights(opponentHeroes);
    }
}
