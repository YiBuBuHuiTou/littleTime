package com.littletime.portal.dao;

import com.littletime.portal.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 14:00
 * @Version 1.0
 **/
public interface PermissionDao extends JpaRepository<Permission, Integer> {
}
