package pers.zhixilang.sc.bill.rpc;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.zhixilang.sc.bill.pojo.UserDto;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 16:45
 */
@FeignClient(name = "sc-user")
public interface UserClient {

    // TODO 增加fallback
    @RequestMapping(method = RequestMethod.GET, path = "/user/info/{id}")
    @Headers("Content-Type: application/json")
    UserDto getUserInfo(@PathVariable("id") Integer id);
}
