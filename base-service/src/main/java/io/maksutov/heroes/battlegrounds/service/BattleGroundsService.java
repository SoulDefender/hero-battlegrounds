package io.maksutov.heroes.battlegrounds.service;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;


public interface BattleGroundsService {

    FightResult sparringHeroes(OpponentHeroes opponents);

}
