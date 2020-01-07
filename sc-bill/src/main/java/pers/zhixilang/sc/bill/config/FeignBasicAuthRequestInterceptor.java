package pers.zhixilang.sc.bill.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-31 18:00
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        requestTemplate.header("x-access-token", servletRequestAttributes.getRequest().getHeader("x-access-token"));
    }

}
