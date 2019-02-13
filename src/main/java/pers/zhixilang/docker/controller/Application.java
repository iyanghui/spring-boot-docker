package pers.zhixilang.docker.controller;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author zhixilang
 * @version 1.0
 * @date 2019-02-13 15:57
 */
@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.LOG);
        application.run(args);
    }
}
