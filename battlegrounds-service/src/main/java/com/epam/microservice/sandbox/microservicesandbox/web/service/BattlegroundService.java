package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.FightResult;
import com.epam.microservice.sandbox.microservicesandbox.model.OpponentHeroes;

/**
 * @author Dmytro_Maksutov
 */
public interface BattlegroundService {

	FightResult heroFights(OpponentHeroes opponents);
}
