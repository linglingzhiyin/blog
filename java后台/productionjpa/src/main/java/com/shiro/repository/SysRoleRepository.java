package com.shiro.repository;

import com.shiro.domain.SysRole;
import com.shiro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole,Integer> {

}
