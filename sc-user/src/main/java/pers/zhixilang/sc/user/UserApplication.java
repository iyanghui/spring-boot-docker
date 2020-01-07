package pers.zhixilang.sc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 15:53
 */
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UserApplication.class);
        application.run(args);
    }
}
