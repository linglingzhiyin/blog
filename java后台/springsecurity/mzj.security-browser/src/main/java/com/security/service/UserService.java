package com.security.service;


import com.security.entiy.SysRole;
import com.security.entiy.User;
import com.security.repository.SysRoleRepository;
import com.security.repository.SysRoleRepository;
import com.security.repository.UserRepository;
import com.util.CustomResult;
import com.util.EUDataGridResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public User getItemById(@PathVariable String id) throws Exception {
        return userRepository.findById(Integer.parseInt(id)).get();
    }

    @RequestMapping(value = "/role/delete_batch")
    @ResponseBody
    private CustomResult removeBatch(Integer[] ids, String userId) throws Exception {
        User user = userRepository.getOne(Integer.parseInt(userId));
        for (Integer i : ids) {
            SysRole sysRole = sysRoleRepository.getOne(i);
            user.getRoleList().remove(sysRole);
        }
        User User1 = userRepository.save(user);
        if (User1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "移除权限失败");
        }
    }

    @PostMapping("/role")
    @ResponseBody
    public CustomResult addRoleList(String temp) throws Exception {
        String[] sts = temp.split("abc");
        SysRole sysRole = sysRoleRepository.getOne(Integer.parseInt(sts[0]));
        User user = userRepository.getOne(Integer.parseInt(sts[1]));
        for (SysRole s : user.getRoleList()) {
            if (s.getRoleId() == Integer.parseInt(sts[0])) {
                return CustomResult.build(101, "添加权限失败");
            }
        }
        user.getRoleList().add(sysRole);
        User User1 = userRepository.save(user);
        if (User1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增权限失败");
        }
    }

    @GetMapping("/role/{id:\\d+}")
    @ResponseBody
    public EUDataGridResult getRoleList(@PathVariable String id) throws Exception {
        if ("111111".equals(id)) return null;
        User user = userRepository.findById(Integer.parseInt(id)).get();
        EUDataGridResult result = new EUDataGridResult();
        List<SysRole> list =new ArrayList<SysRole>();
        HashSet h = new HashSet(user.getRoleList());
        list.addAll(h);
        result.setRows(list);
        result.setTotal(list.size());
        return result;
    }


    @GetMapping
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam Integer page, @RequestParam Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<User> list = userRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @PostMapping
    @ResponseBody
    private CustomResult insert(@Valid User user, BindingResult bindingResult) throws Exception {
        user.setSalt("1");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getUserId() != null)
            return update(user, bindingResult);
        User User1 = userRepository.save(user);
        if (User1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增角色失败");
        }
    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult update(@Valid User User, BindingResult bindingResult) throws Exception {
        User User1 = userRepository.save(User);
        if (User1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "更新角色失败");
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Integer[] ids) throws Exception {
        try {
            userRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }


    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    private CustomResult delete(@PathVariable String id) throws Exception {
        try {
            userRepository.deleteById(Integer.parseInt(id));
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }
}
