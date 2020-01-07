package pers.zhixilang.sc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 17:44
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EurekaServerApplication.class);
        application.run(args);
    }
}
