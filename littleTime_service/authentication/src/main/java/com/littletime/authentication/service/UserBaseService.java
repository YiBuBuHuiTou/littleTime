package com.littletime.authentication.service;

import com.littletime.authentication.Bean.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
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
    User addUser(User user, int tenantId);

    /**
     * 删除用户
     * @return
     */
    User deleteUserByCredential(String credential);

    /**
     *修改用户
     */
    User updateUser(User user);

    /**
     * 检索所有用户
     * @return
     */
    List<User> searchAllUsers();

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    User findByUserName(String userName);

    /**
     * 登录用户
     * @param user
     * @return
     */
    User signIn(User user);

    /**
     * 生成jwt token
     * @param ttl
     * @return
     */
    String generateToken(User user, long ttl, long redisTTL);

    /**
     * 判断用户是否存在
     * @param userName
     * @return
     */
    boolean checkUserExist(String userName);
}
