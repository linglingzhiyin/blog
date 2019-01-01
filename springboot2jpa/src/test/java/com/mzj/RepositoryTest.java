package com.mzj;

import com.domain.SysRole;
import com.domain.SysUser;
import com.repository.SysPermissionRepository;
import com.repository.SysRoleRepository;
import com.repository.SysUesrRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RepositoryTest {
    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysUesrRepository sysUesrRepository;

    @Test
    public void saveTest() throws Exception {
        SysRole sysRole1 = new SysRole();
        SysRole sysRole2 = new SysRole();
        sysRole1.setRoleName("admin");
        sysRole2.setRoleName("user");
        SysUser sysUser = new SysUser();
        sysUser.setUsername("user");
        sysUser.setRoles(new HashSet<SysRole>() {
            {
                add(sysRole1);
                add(sysRole2);
            }
        });
        sysRoleRepository.save(sysRole1);
        sysRoleRepository.save(sysRole2);
        sysUesrRepository.save(sysUser);

    }

    @Test
    public void findAllTest() throws Exception {
        List<SysRole> roles = sysRoleRepository.findAll();
        for (SysRole role : roles) {
            log.info(role.getRoleName());
        }
    }
    @Test
    public void Test1() throws Exception {
        List<SysUser> roles = sysUesrRepository.findByNameTest("us");
        for (SysUser role : roles) {
            log.info(role.getUsername());
        }
    }
}
