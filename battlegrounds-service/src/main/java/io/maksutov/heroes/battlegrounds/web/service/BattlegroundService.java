package io.maksutov.heroes.battlegrounds.web.service;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;


/**
 * @author Dmytro_Maksutov
 */
public interface BattlegroundService {

	FightResult heroFights(OpponentHeroes opponents);
}
