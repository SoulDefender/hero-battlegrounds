package io.maksutov.heroes.battlegrounds.remote.config;

import feign.Logger;
import feign.Logger.Level;
import io.maksutov.heroes.battlegrounds.remote.BattlegroundsClient;
import io.maksutov.heroes.battlegrounds.remote.HeroDataClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmytro_Maksutov
 */

@Configuration
public class FeignConfig
{
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }


    @Bean HeroDataClient.HeroDataClientFallback heroDataClient() {

        return new HeroDataClient.HeroDataClientFallback();
    }


    @Bean BattlegroundsClient.BattlegroundFallback battlegroundFallback() {

        return new BattlegroundsClient.BattlegroundFallback();
    }
    
}
