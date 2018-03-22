package io.maksutov.heroes.battlegrounds.model;

import io.maksutov.heroes.battlegrounds.utils.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@RedisHash("CustomToken")
public class CustomToken implements Serializable {

    @Id
    private String token;

    private User user;

    @TimeToLive
    private long timeToLive = Constants.DEFAULT_TOKEN_TTL;

    public CustomToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public CustomToken(String token, User user, long timeToLive) {
        this(token, user);
        this.timeToLive = timeToLive;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public long getTimeToLive() {
        return timeToLive;
    }
}
