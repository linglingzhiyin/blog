package com.security.repository;

import com.security.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);

    @Modifying
    @Transactional
    @Query("DELETE from User p where p.userId in ?1 ")
    void deleteByIds(Integer[] ids);
}
