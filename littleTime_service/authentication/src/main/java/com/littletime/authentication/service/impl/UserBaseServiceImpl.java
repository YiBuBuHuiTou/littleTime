package com.littletime.authentication.service.impl;

import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.Tenant;
import com.littletime.authentication.Bean.User;
import com.littletime.authentication.dao.TenantDao;
import com.littletime.authentication.dao.UserDao;
import com.littletime.authentication.service.UserBaseService;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public UserBaseServiceImpl(UserDao userDao, TenantDao tenantDao) {
        this.userDao = userDao;
        this.tenantDao = tenantDao;
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
                    @CacheEvict(key = "#user_id"),
                    @CacheEvict(key = "#root.target.targetUser.credential"),
                    @CacheEvict(key = "#root.target.targetUser.userName")
            }
    )
    public Boolean deleteUserById(Long user_id) {
        boolean defaultResult = false;
        if ( user_id != null && user_id > 0) {
            targetUser = userDao.findById(user_id).orElse(null);
            if (targetUser != null)  {
                try {
                    userDao.deleteById(user_id);
                    defaultResult = true;
                }catch (EmptyResultDataAccessException e) {
                    LOGGER.error("no entity exits. userId = " + user_id, e);
                }
            } else {
                LOGGER.error("no entity exits. userId = " + user_id);
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
                    @CachePut(key = "#result.id"),
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
     * 根据用户id查询用户信息
     *
     * @param user_id 用户id
     * @return
     */
    @Override
    @Caching(
            cacheable = {
              @Cacheable(key = "#user_id",unless = "#result == null")
            },
            put = {
                    @CachePut(key = "#result.credential"),
                    @CachePut(key = "#result.userName")
            }
    )
    public User findById(Long user_id) {
        if (user_id == null) {
            return null;
        }
        return userDao.getOne(user_id);
    }
}
