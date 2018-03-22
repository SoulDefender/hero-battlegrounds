package io.maksutov.heroes.battlegrounds.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

/**
 * @author Dmytro_Maksutov
 */
@Log
public class SimpleFilter extends ZuulFilter
{
    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return 1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
