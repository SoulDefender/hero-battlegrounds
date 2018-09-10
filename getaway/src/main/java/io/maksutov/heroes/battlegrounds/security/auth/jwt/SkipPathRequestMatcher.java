package io.maksutov.heroes.battlegrounds.security.auth.jwt;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * SkipPathRequestMatcher
 *
 * @author vladimir.stankovic, dmitry.maksutov
 * <p>
 * Aug 19, 2016
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private final List<HttpMethod> methods;
    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;


    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath, HttpMethod... methods) {

        Assert.notNull(pathsToSkip, "Paths should not be null");
        List<RequestMatcher> m = pathsToSkip.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        matchers = new OrRequestMatcher(m);
        processingMatcher = new AntPathRequestMatcher(processingPath);
        this.methods = Arrays.asList(methods);
    }


    @Override
    public boolean matches(HttpServletRequest request) {

        return !matchers.matches(request) && processingMatcher.matches(request) &&
                methods.contains(HttpMethod.resolve(
                        request.getMethod()));
    }
}
