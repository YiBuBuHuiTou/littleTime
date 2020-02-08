package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListDao extends JpaRepository<BlackList, Integer> {
}
