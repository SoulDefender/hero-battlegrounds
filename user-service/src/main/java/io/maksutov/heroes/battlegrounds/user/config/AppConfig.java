package io.maksutov.heroes.battlegrounds.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Dmytro_Maksutov
 */
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
