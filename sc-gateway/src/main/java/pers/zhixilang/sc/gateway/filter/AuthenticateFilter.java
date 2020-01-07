package pers.zhixilang.sc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pers.zhixilang.sc.gateway.util.JwtUtil;
import pers.zhixilang.sc.pojo.ResultInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 认证
 * @author zhixilang
 * @version 1.0
 * date 2019-12-31 16:43
 */
@Component
public class AuthenticateFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateFilter.class);

    @Resource
    private JwtUtil jwtUtil;

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        return !requestContext.getRequest().getRequestURI().contains("/login");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("x-access-token");

        ResultInfo<String> resultInfo = new ResultInfo<>();
        resultInfo.setCode(-1);

        if (StringUtils.isEmpty(token)) {
            resultInfo.setMsg("token不能为空");
            context.setResponseBody(JSON.toJSONString(resultInfo));
            context.getResponse().setContentType("application/json;charset=UTF-8");
            context.setSendZuulResponse(false);
            return null;
        }
        LOGGER.info("===request url: {}, token: {}", request.getRequestURL(), token);
        /*if (jwtUtil.validateJWT(token)) {
            return null;
        }*/
        resultInfo.setMsg("校验失败");
        context.setResponseBody(JSON.toJSONString(resultInfo));
        context.getResponse().setContentType("application/json;charset=UTF-8");
//        context.setSendZuulResponse(false);
        return null;
    }
}
