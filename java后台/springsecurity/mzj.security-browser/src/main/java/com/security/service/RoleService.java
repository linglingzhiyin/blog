package com.security.service;


import com.security.entiy.SysPermission;
import com.security.entiy.SysRole;
import com.security.repository.SysPermissionRepository;
import com.security.repository.SysRoleRepository;
import com.util.CustomResult;
import com.util.EUDataGridResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @GetMapping("/Data")
    @ResponseBody
    public List<SysRole> getAll() throws Exception {
        return sysRoleRepository.findAll();
    }
    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public SysRole getItemById(@PathVariable String id) throws Exception {
        return sysRoleRepository.findById(Integer.parseInt(id)).get();
    }

    @RequestMapping(value = "/permission/delete_batch")
    @ResponseBody
    private CustomResult removeBatch(Integer[] ids, String roleId) throws Exception {
        SysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(roleId));
        for (Integer i : ids) {
            SysPermission sysPermission = sysPermissionRepository.getOne(i);
            sysRole.getPermissions().remove(sysPermission);
        }
        SysRole sysRole1 = sysRoleRepository.save(sysRole);
        if (sysRole1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "移除权限失败");
        }
    }

    @PostMapping("/permission")
    @ResponseBody
    public CustomResult addPermissionList(String temp) throws Exception {
        String[] sts = temp.split("abc");
        SysPermission sysPermission = sysPermissionRepository.getOne(Integer.parseInt(sts[0]));
        SysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(sts[1]));
        for (SysPermission s : sysRole.getPermissions()) {
            if (s.getPermissionId() == Integer.parseInt(sts[0])) {
                return CustomResult.build(101, "添加权限失败");
            }
        }
        sysRole.getPermissions().add(sysPermission);
        SysRole sysRole1 = sysRoleRepository.save(sysRole);
        if (sysRole1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增权限失败");
        }
    }

    @GetMapping("/permission/{id:\\d+}")
    @ResponseBody
    public EUDataGridResult getPermissionList(@PathVariable String id) throws Exception {
        if ("111111".equals(id)) return null;
        SysRole role = sysRoleRepository.findById(Integer.parseInt(id)).get();
        EUDataGridResult result = new EUDataGridResult();
        List<SysPermission> list = role.getPermissions();
        result.setRows(list);
        result.setTotal(list.size());
        return result;
    }


    @GetMapping
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam Integer page, @RequestParam Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<SysRole> list = sysRoleRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @PostMapping
    @ResponseBody
    private CustomResult insert(@Valid SysRole sysRole, BindingResult bindingResult) throws Exception {
        if (sysRole.getRoleId() != null)
            return update(sysRole, bindingResult);
        SysRole sysRole1 = sysRoleRepository.save(sysRole);
        if (sysRole1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增角色失败");
        }
    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult update(@Valid SysRole sysRole, BindingResult bindingResult) throws Exception {


        SysRole sysRole1 = sysRoleRepository.save(sysRole);
        if (sysRole1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "更新角色失败");
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Integer[] ids) throws Exception {
        try {
            sysRoleRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }


    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult delete(@PathVariable String id) throws Exception {
        try {
            sysRoleRepository.deleteById(Integer.parseInt(id));
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }
}
