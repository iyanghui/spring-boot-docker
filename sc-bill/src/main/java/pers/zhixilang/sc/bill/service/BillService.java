package pers.zhixilang.sc.bill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pers.zhixilang.sc.bill.pojo.BillDto;
import pers.zhixilang.sc.bill.pojo.UserDto;
import pers.zhixilang.sc.bill.rpc.UserClient;

import javax.annotation.Resource;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 16:31
 */
@Service
public class BillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);


    @Resource
    private UserClient userClient;

    public BillDto getBill(Integer id) {
        BillDto billDto = new BillDto();
        billDto.setId(id);
        billDto.setName("bill_" + id);

        try {
            UserDto userDto = userClient.getUserInfo(id);
            billDto.setUserId(userDto.getId());
            billDto.setUserName(userDto.getName());
        } catch (Exception e) {
            LOGGER.error("=== rpc [bill -> user] error: ", e);
        }

        return billDto;
    }
}
