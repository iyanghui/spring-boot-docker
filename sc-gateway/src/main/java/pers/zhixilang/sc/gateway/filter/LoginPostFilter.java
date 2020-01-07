package pers.zhixilang.sc.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import pers.zhixilang.sc.gateway.util.JwtUtil;
import pers.zhixilang.sc.pojo.Constants;
import pers.zhixilang.sc.pojo.ResultInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @author zhixilang
 * @version 1.0
 * date 2020-01-02 11:04
 */
@Component
public class LoginPostFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPostFilter.class);

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return requestContext.getRequest().getRequestURI().contains("/login") && requestContext.sendZuulResponse();
    }

    // REMARK 须做好约束，登录成功仅返回id，后续所有操作(获取信息、系统参数、数据字典等)
    // 均需要携带token
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();

        try {

            InputStream is = requestContext.getResponseDataStream();
            String responseAsString = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
            LOGGER.info("== response body: {}", responseAsString);

            ResultInfo<String> resultInfo = JSONObject.parseObject(responseAsString,
                    new TypeReference<ResultInfo<String>>(){});

            requestContext.setResponseBody(responseAsString);

            if (!Constants.RES_SUCCESS_CODE.equals(resultInfo.getCode())) {
                requestContext.setSendZuulResponse(false);
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                return null;
            }

            String token = jwtUtil.createJWT(resultInfo.getData(), new HashMap<>());
            response.setHeader("x-access-token", token);
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
        return null;
    }
}
