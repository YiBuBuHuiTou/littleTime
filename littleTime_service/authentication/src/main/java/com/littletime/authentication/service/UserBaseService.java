package com.littletime.authentication.service;

import com.littletime.authentication.Bean.User;

import java.util.List;

/**
 * @author YiBuBuHuiTou
 * 用户基本操作service
 */
public interface UserBaseService {

    /**
     * 追加用户
     * @return
     */
    Boolean addUser(User user);

    /**
     * 删除用户
     * @return
     */
    Boolean deleteUserById(Long user_id);

    /**
     *修改用户
     */
    Boolean updateUser(User user);

    /**
     * 检索所有用户
     * @return
     */
    List<User> searchAllUsers();

    /**
     * 根据用户id查询用户信息
     * @param user_id 用户id
     * @return
     */
    User findById(Long user_id);

}
