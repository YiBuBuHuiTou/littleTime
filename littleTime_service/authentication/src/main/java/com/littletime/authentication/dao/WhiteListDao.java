package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.WhiteList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhiteListDao extends JpaRepository<WhiteList, Integer> {


}
