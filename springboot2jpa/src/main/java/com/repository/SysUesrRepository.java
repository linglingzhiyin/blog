package com.repository;

import com.domain.SysRole;
import com.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUesrRepository extends JpaRepository<SysUser, Integer> {
    @Query("select O from SysUser O where O.username like %?1%")
    public List<SysUser> findByNameTest(String name);
}
