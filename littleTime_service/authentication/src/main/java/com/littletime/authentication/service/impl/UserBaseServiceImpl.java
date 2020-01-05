package com.littletime.authentication.service.impl;

import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.User;
import com.littletime.authentication.dao.UserDao;
import com.littletime.authentication.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YiBuBuHuiTou
 * 用户基本操作实现类
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

    /**
     * 注入UserDao
     * @param userDao
     */
    @Autowired
    public UserBaseServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    /**
     * 用户数据库操作类
     */
    private UserDao userDao;

    /**
     * 追加用户
     *
     * @return
     */
    @Override
    public Boolean addUser(User user) {
        // 字段飞空判断
        boolean defaultResult = false;
        if (user != null && !StringUtils.isEmpty(user.getUserName()) &&
                            !StringUtils.isEmpty(user.getCredential()) &&
                            !StringUtils.isEmpty(user.getPassword())) {
            try {
                //TODO 生成用户其他信息
                userDao.save(user);
                defaultResult = true;
            }catch (Exception e) {
                //TODO
                e.printStackTrace();
            }
        }
        return defaultResult;
    }

    /**
     * 删除用户
     *
     * @return
     */
    @Override
    public Boolean deleteUserById(Long user_id) {
        boolean defaultResult = false;
        if ( user_id != null) {
            userDao.deleteById(user_id);
            defaultResult = true;
        }
        return defaultResult;
    }

    /**
     * 修改用户
     */
    @Override
    public Boolean updateUser(User user) {
        boolean defaultResult = false;
        if (user.getId() != null) {
            userDao.save(user);
        } else {
        //TODO
        }
        return defaultResult;
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
    public User findById(Long user_id) {
        if (user_id == null) {
            return null;
        }
        return userDao.getOne(user_id);
    }
}
