package com.littletime.portal.dao;

import com.littletime.portal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 14:02
 * @Version 1.0
 **/
public interface RoleDao extends JpaRepository<Role, Integer> {
}
