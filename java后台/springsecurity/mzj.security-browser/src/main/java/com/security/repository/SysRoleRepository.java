package com.security.repository;

import com.security.entiy.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE from SysRole p where p.roleId in ?1 ")
    void deleteByIds(Integer[] ids);
}
