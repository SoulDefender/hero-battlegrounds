package io.maksutov.heroes.battlegrounds.remote.config;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmytro_Maksutov
 */

@Configuration
public class FeignConfig
{
    
    @Bean
    Logger.Level feingLoggerLevel() {
        return Level.FULL;
    }
    
}
