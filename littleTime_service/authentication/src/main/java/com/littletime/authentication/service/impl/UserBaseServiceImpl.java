package com.littletime.authentication.service.impl;

import com.alibaba.fastjson.JSON;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Throwable.class)
    @Caching(
            evict = {
                    @CacheEvict(key = "#result.userName",condition = "#result != null"),
                    @CacheEvict(key = "#result.credential",condition = "#result != null")
            }
    )
    public User deleteUserByCredential(String credential) {
        User targetUser = null;
        if (!StringUtils.isEmpty(credential)) {
             targetUser = userDao.findFirstByCredential(credential);
        }
        if (targetUser != null)  {
            int result = -1;
            try {
               result =  userDao.deleteByCredential(credential);
            }catch (EmptyResultDataAccessException e) {
                LOGGER.error("no entity exits. credential = " + credential, e);
            }
            if (result == 1) {
                return targetUser;
            }
            else {
                return null;
            }
        } else {
            LOGGER.error("no entity exits. credential = " + credential);
            return null;
        }

    }

    /**
     * 修改用户
     */
    @Override
    @Caching(
            put = {
                    @CachePut(key = "#result.credential"),
                    @CachePut(key = "#result.userName")
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
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return
     */
    @Override
    @Caching(
            cacheable = {
                    @Cacheable(key = "#userName", unless = "#result == null")
            },
            put = {
                    @CachePut(key = "#result.credential", unless = "#result == null")
            }
    )
    public User findByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        return userDao.findFirstByUserName(userName);
    }

    @Override
    public User signIn(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        User selectUser = userDao.findFirstByUserName(userName);
        if (selectUser == null) {
            return null;
        }

        if (StringUtils.isEmpty(selectUser.getPassword()) && StringUtils.isEmpty(password)) {
            return selectUser;
        }

        if (!StringUtils.isEmpty(selectUser.getPassword()) &&
                !StringUtils.isEmpty(password) &&
                selectUser.getPassword().equals(password)) {

                return selectUser;
        }
        return null;
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

    @Override
    public boolean checkUserExist(String userName) {
        return userDao.existsByUserName(userName);
    }

}

