package pers.zhixilang.sc.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhixilang
 * @version 1.0
 * date 2020-01-06 15:05
 */
@Component
public class ResetRouteFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetRouteFilter.class);

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        LOGGER.info(requestContext.getRouteHost().toString());

        return null;
    }
}
