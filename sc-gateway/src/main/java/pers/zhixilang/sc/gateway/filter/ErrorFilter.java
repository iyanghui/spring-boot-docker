package pers.zhixilang.sc.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理过滤器，zuul自带返回过于粗糙
 * @author zhixilang
 * @version 1.0
 * date 2020-01-02 14:14
 */
@Component
public class ErrorFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            Throwable throwable = ctx.getThrowable();
            LOGGER.error("记录zuul调用服务时发生的异常信息： ", throwable);

            RequestDispatcher dispatcher = request.getRequestDispatcher(this.errorPath);
            if (dispatcher != null) {
                ctx.set("sendErrorFilter.ran", true);
                if (!ctx.getResponse().isCommitted()) {
                    ctx.setResponseBody("{\n" +
                            "  \"code\": -1,\n" +
                            "  \"msg\": \"调用异常\"\n" +
                            "}");
                    dispatcher.forward(request, ctx.getResponse());
                }
            }
        } catch (Exception ex) {
            // TODO 记录日志
            LOGGER.error("系统异常", ex);
        }
        return null;
    }
}
