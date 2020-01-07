package pers.zhixilang.sc.bill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-31 18:00
 */
@Configuration
public class FeignConfig {

    // TODO 统一请求拦截器，header增加token

    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * @return FeignBasicAuthRequestInterceptor
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
}
