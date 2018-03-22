package io.maksutov.heroes.battlegrounds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BattlegroundsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BattlegroundsApplication.class, args);
    }
}
