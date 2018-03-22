package io.maksutov.heroes.battlegrounds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class Config {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration("localhost",
                6379);
        return new JedisConnectionFactory(rsc);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jcf) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jcf);
        return template;
    }

}
