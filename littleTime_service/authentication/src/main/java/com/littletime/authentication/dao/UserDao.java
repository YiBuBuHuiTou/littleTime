package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    public User findByCredential(String credential);

    public User findByUserName(String userName);

    public boolean existsByCredential(String credential);

    public boolean existsByUserName(String userName);

}
