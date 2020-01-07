package pers.zhixilang.sc.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 15:54
 */
@SpringBootApplication
@EnableFeignClients
public class BillApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BillApplication.class);
        application.run(args);
    }
}
