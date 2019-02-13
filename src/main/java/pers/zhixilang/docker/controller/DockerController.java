package pers.zhixilang.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhixilang
 * @version 1.0
 * @date 2019-02-13 15:57
 */
@RestController
public class DockerController {
    @RequestMapping("/")
    public String home() {
        return "hello world\nhello docker";
    }
}
