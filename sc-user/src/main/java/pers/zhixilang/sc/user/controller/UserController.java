package pers.zhixilang.sc.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zhixilang.sc.pojo.ResultInfo;
import pers.zhixilang.sc.user.pojo.UserDto;
import pers.zhixilang.sc.user.pojo.UserEntity;
import pers.zhixilang.sc.user.service.UserService;

import javax.annotation.Resource;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 16:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Value("${eureka.client.service-url.defaultZone}")
    private String defaultZone;

    @PostMapping(value = "/login")
    public ResultInfo<String> login(@RequestBody UserEntity userEntity) {
        System.out.println("===defaultZone: " + defaultZone);

        ResultInfo<String> resultInfo = new ResultInfo<>();
        UserDto userDto = userService.login(userEntity);
        if (null != userDto) {
            resultInfo.setCode(1);
            resultInfo.setData(userDto.getId().toString());
            return resultInfo;
        }
        resultInfo.setCode(-1);
        resultInfo.setMsg("用户不存在");
        return resultInfo;
    }

    @GetMapping(value = "/info/{id}")
    public UserDto getUser(@PathVariable(value = "id") Integer id,
                           @RequestHeader(value = "x-access-token") String token) {
        System.out.println("token: " + token);

        return userService.getUser(id);
    }
}
