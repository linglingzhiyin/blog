package com.security.service;


import com.security.entiy.SysPermission;
import com.security.repository.SysPermissionRepository;
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
@RequestMapping("/permission")
@Slf4j
public class PermissionService {

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public SysPermission getItemById(@PathVariable String id) throws Exception {
        return sysPermissionRepository.findById(Integer.parseInt(id)).get();
    }
    @GetMapping("/Data")
    @ResponseBody
    public List<SysPermission> getAll() throws Exception {
        return sysPermissionRepository.findAll();
    }

    @GetMapping
//    @Cacheable(key = "'sysPermissionList'")
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam Integer page, @RequestParam Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<SysPermission> list = sysPermissionRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @PostMapping
    @ResponseBody
    private CustomResult insert(@Valid SysPermission sysPermission, BindingResult bindingResult) throws Exception {
        if (sysPermission.getPermissionId() != null)
            return update(sysPermission, bindingResult);
        SysPermission sysPermission1 = sysPermissionRepository.save(sysPermission);
        if (sysPermission1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增权限失败");
        }
    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult update(@Valid SysPermission sysPermission, BindingResult bindingResult) throws Exception {


        SysPermission sysPermission1 = sysPermissionRepository.save(sysPermission);
        if (sysPermission1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "更新权限失败");
        }
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Integer[] ids) throws Exception {
        try {
            sysPermissionRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }


    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult delete(@PathVariable String id) throws Exception {
        try {
            sysPermissionRepository.deleteById(Integer.parseInt(id));
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }
}
