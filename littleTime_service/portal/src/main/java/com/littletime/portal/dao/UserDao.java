package com.littletime.portal.dao;

import com.littletime.portal.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 23:19
 * @Version 1.0
 **/
public interface UserDao extends JpaRepository<UserInfo, Integer> {
}
