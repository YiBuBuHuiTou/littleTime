package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    public User findFirstByCredential(String credential);

    public User findFirstByUserName(String userName);

    public boolean existsByCredential(String credential);

    public boolean existsByUserName(String userName);

    public boolean deleteByCredential(String credential);
}
