package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {


    public User findFirstByUserName(String userName);

    public User findFirstByCredential(String credential);

    public boolean existsByCredential(String credential);

    public boolean existsByUserName(String userName);

    public int deleteByCredential(String credential);
}
