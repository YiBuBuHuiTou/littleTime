package com.littletime.authentication.service.impl;

import com.alibaba.fastjson.JSON;
import com.cxd.littletime.common.constant.ENCRYPT_TYPE;
import com.cxd.littletime.common.util.CryptoUtils;
import com.cxd.littletime.common.util.JWTUtils;
import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.User;
import com.littletime.authentication.dao.TenantDao;
import com.littletime.authentication.dao.UserDao;
import com.littletime.authentication.service.UserBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author YiBuBuHuiTou
 * 用户基本操作实现类
 */
@CacheConfig(cacheNames = "auth_user")
@Service
public class UserBaseServiceImpl implements UserBaseService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserBaseServiceImpl.class);

    private User targetUser = null;
    /**
     * 注入UserDao
     * @param userDao
     */
    @Autowired
    public UserBaseServiceImpl(UserDao userDao, TenantDao tenantDao, RedisTemplate redisTemplate) {
        this.userDao = userDao;
        this.tenantDao = tenantDao;
        this.redisTemplate = redisTemplate;
    }
    /**
     * 用户数据库操作类
     */
    private UserDao userDao;

    /**
     * tenant 数据库操作
     */
    private TenantDao tenantDao;

    /**
     * redis 操作
     */
    private RedisTemplate redisTemplate;
    /**
     * 追加用户
     *
     * @return
     */
    @Override
    public User addUser(User user, int tenantId) {

        User result = null;
        //非空判断
        if (user != null) {
            // 生成身份id
            if (StringUtils.isEmpty(user.getCredential())) {
                String credential = null;
                boolean credflag = false;
                do {
                    credential = StringUtils.generateCredential();
                    if (!userDao.existsByCredential(credential)) {
                        credflag = true;
                    }
                } while (!credflag);
                user.setCredential(credential);
            } else {
                LOGGER.warn("credential string is already exists. please check the user's information is valid or not! credential = " + user.getCredential());
            }
            try {
                // 设置tenant租户
                user.setTenant(tenantDao.getOne(tenantId));
                result = userDao.save(user);
            }catch (Exception e) {
                LOGGER.warn("tenant is not exist. please check the tenantId is correct or not! tenantId = " + tenantId);
            }
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @return
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(key = "#root.target.targetUser.credential")
            }
    )
    public boolean deleteUserByCredential(String credential) {
        boolean defaultResult = false;
        if (!StringUtils.isEmpty(credential)) {
            targetUser = userDao.findFirstByCredential(credential);
            if (targetUser != null)  {
                try {
                    userDao.deleteByCredential(credential);
                    defaultResult = true;
                }catch (EmptyResultDataAccessException e) {
                    LOGGER.error("no entity exits. credential = " + credential, e);
                }
            } else {
                LOGGER.error("no entity exits. credential = " + credential);
            }

        }
        return defaultResult;
    }

    /**
     * 修改用户
     */
    @Override
    @Caching(
            put = {
                    @CachePut(key = "#result.credential")
            }
    )
    public User updateUser(User user) {
        boolean defaultResult = false;
        if (user.getId() != null && userDao.existsById(user.getId())) {
            userDao.save(user);
            defaultResult = true;
        } else {
            LOGGER.warn("can not update entity, userId is not exist!");
        }
        if (defaultResult) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 检索所有用户
     *
     * @return
     */
    @Override
    public List<User> searchAllUsers() {
        return userDao.findAll();
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param user_id 用户id
     * @return
     */
    @Override
    @Caching(
            put = {
                    @CachePut(key = "#result.credential", unless = "#result == null"),
            }
    )
    public User findById(Long user_id) {
        if (user_id == null) {
            return null;
        }
        return userDao.getOne(user_id);
    }

    @Override
    public boolean signIn(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isEmpty(userName)) {
            return false;
        }
        User selectUser = userDao.findFirstByUserName(userName);
        if (selectUser == null) {
            return false;
        }

        if (StringUtils.isEmpty(selectUser.getPassword()) && StringUtils.isEmpty(password)) {
            return true;
        }

        if (!StringUtils.isEmpty(selectUser.getPassword()) &&
                !StringUtils.isEmpty(password) &&
                selectUser.getPassword().equals(password)) {

                return true;
        }
        return false;
    }

    @Override
    public String generateToken(User user, long tokenTtl, long redisTTL) {
        String token = null;
        if (user != null && !StringUtils.isEmpty(user.getCredential())) {
            Map<String, String> subject = null;
            if (!StringUtils.isEmpty(user.getUserName())) {
                subject = new HashMap<>();
                subject.put("userName", user.getUserName());
            }
            token = JWTUtils.createJWT(user.getCredential(), JSON.toJSONString(subject),tokenTtl);
        }
        redisTemplate.opsForValue().set(user.getCredential(), token);
        if (redisTTL > 0) {
            redisTemplate.expire(user.getCredential(), redisTTL, TimeUnit.SECONDS);
        }
        return token;
    }
}
