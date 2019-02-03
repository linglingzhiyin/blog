package com.security.repository;

import com.security.entiy.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysPermissionRepository extends JpaRepository<SysPermission,Integer> {
    @Modifying
    @Transactional
    @Query("DELETE from SysPermission p where p.permissionId in ?1 ")
    void deleteByIds(Integer[] ids);
}
