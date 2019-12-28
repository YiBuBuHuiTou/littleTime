package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
}
