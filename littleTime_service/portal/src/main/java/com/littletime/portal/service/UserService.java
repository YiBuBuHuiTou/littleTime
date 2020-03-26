package com.littletime.portal.service;

import com.littletime.portal.model.dto.UserInfoDto;
import org.springframework.stereotype.Service;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 0:02
 * @Version 1.0
 **/
public interface UserService {
    boolean checkIsActiveBy(String credential);
    UserInfoDto findUserByUserNameOrPhoneOrEmail(String account);

}
