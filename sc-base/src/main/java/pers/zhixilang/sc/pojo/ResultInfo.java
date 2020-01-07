package pers.zhixilang.sc.pojo;

import lombok.Data;

/**
 * @author zhixilang
 * @version 1.0
 * date 2020-01-02 11:20
 */
@Data
public class ResultInfo<T> {
    private Integer code;

    private String msg;

    private T data;
}
