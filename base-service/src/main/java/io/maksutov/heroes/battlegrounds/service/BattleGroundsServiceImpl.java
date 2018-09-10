package io.maksutov.heroes.battlegrounds.service;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import io.maksutov.heroes.battlegrounds.remote.BattlegroundsClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class BattleGroundsServiceImpl implements BattleGroundsService {

    private final BattlegroundsClient battlegroundsClient;


    public BattleGroundsServiceImpl(BattlegroundsClient battlegroundsClient) {

        this.battlegroundsClient = battlegroundsClient;
    }


    @Override
    public FightResult sparringHeroes(OpponentHeroes opponents) {

        return battlegroundsClient.sparring(opponents);
    }
}
