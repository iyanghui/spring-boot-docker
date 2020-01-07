package pers.zhixilang.sc.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * 鉴权
 * @author zhixilang
 * @version 1.0
 * date 2019-12-31 17:31
 */
@Component
public class AuthenticationFilter extends ZuulFilter {
    // TODO 所有权限存储至redis

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
