package pers.zhixilang.sc.bill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zhixilang.sc.bill.pojo.BillDto;
import pers.zhixilang.sc.bill.service.BillService;

import javax.annotation.Resource;

/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 16:30
 */
@RestController
@RequestMapping(value = "/bill")
public class BillController {

    @Resource
    private BillService billService;

    @GetMapping(value = "/info/{id}")
    public BillDto getBill(@PathVariable(value = "id") Integer id) {
        return billService.getBill(id);
    }

}
