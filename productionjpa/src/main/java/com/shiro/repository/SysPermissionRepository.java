package com.shiro.repository;

import com.shiro.domain.SysPermission;
import com.shiro.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission,Integer> {

}
