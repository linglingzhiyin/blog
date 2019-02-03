package com.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.web.dto.User;
import com.web.dto.UserQueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @DeleteMapping("/{id:\\d+}")
    public String delete(@PathVariable(value = "id",
            required = false) String id) {
        log.info(id);
        return "删除成功";
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                        System.out.println(error.getDefaultMessage());
                    }
            );
        }
        log.info(user.getBirthday().toString());
        log.info(user.toString());
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(
                    error -> System.out.println(error.getDefaultMessage()));
        }

        log.info(user.getBirthday().toString());
        user.setId("1");
        log.info(user.toString());
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition user
            , @PageableDefault(page = 1, size = 10, sort = "username,asc") Pageable pageable) {
        log.info(user.toString());
        List<User> users = new ArrayList<User>();
        users.add(new User("aaa", "bbb"));
        users.add(new User("bbb", "ccc"));
        users.add(new User("ccc", "ddd"));
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(value = "id",
            required = false) String id) {
        log.info(id);
        return new User("aaa", "bbb");
    }
}
