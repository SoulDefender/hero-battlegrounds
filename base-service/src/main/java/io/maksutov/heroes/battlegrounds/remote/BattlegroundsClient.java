package io.maksutov.heroes.battlegrounds.remote;

import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import io.maksutov.heroes.battlegrounds.web.BattleController;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(
        name = "battlegrounds-service",
        url = "http://localhost:7666",
        fallback = BattlegroundsClient.BattlegroundFallback.class
)
public interface BattlegroundsClient extends BattleController {

    class BattlegroundFallback implements BattlegroundsClient {

        @Override
        public FightResult sparring(OpponentHeroes opponentHeroes) {

            return new FightResult();
        }
    }
}
