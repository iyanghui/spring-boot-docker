package pers.zhixilang.sc.user.service;

import org.springframework.stereotype.Service;
import pers.zhixilang.sc.user.pojo.UserDto;
import pers.zhixilang.sc.user.pojo.UserEntity;


/**
 * @author zhixilang
 * @version 1.0
 * date 2019-12-30 16:00
 */
@Service
public class UserService {
    public UserDto getUser(Integer id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("hello_" + id);
        userDto.setAge(18);
        return userDto;
    }

    public UserDto login(UserEntity entity) {
        if (!"admin".equals(entity.getUserName())) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(999);
        userDto.setName(entity.getUserName());
        return userDto;
    }

}
