package com.littletime.portal.service.impl;

import com.littletime.portal.dao.PermissionDao;
import com.littletime.portal.dao.RoleDao;
import com.littletime.portal.model.Permission;
import com.littletime.portal.model.Role;
import com.littletime.portal.service.ApplicationCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 14:00
 * @Version 1.0
 **/
@Service
public class ApplicationCommonServiceImpl implements ApplicationCommonService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(ApplicationCommonServiceImpl.class);
    private PermissionDao permissionDao;

    private RoleDao roleDao;

    @Autowired
    ApplicationCommonServiceImpl(PermissionDao permissionDao, RoleDao roleDao) {
        this.permissionDao = permissionDao;
        this.roleDao = roleDao;
    }


    @Override
    public boolean preInsertDB() {
        LOGGER.info("preInsertDB start");
        if (!roleDao.findAll().isEmpty() ||  !permissionDao.findAll().isEmpty()) {
            return true;
        }
        List<Permission> permissionList = new ArrayList<>();
        for (int i = 0; i< 32; i++) {
            Permission permission = new Permission();
            permission.setBitNum(i);
            permission.setDescription("BIT"+ i);
            permissionDao.save(permission);
            permissionList.add(permission);
        }

        Role administrator = new Role();
        administrator.setRoleName("Administrator");
        administrator.setDescription("Administrator");
        Role admin = new Role();
        admin.setRoleName("admin");
        admin.setDescription("admin");
        Role user = new Role();
        user.setRoleName("user");
        user.setDescription("user");
        List<Role> roleList = new ArrayList<>();
        roleList.add(administrator);
        roleList.add(admin);
        roleList.add(user);

        return !permissionDao.saveAll(permissionList).isEmpty() && !roleDao.saveAll(roleList).isEmpty();
    }


}
