package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("search")
    public Result<List<User>> getName(String keyword) {
        return service.getName(keyword);
    }

    /**
     * 增
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<String> insertUser(User user){
        return service.insertUser(user);
    }

    /**
     * 查
     * @return
     */
    @GetMapping("/select")
    public Result<List<User>> selectUser(){
        return service.selectUser();
    }

    /**
     * 删
     * @param name
     * @return
     */
    @PostMapping("/delete")
    public Result<String> delete(String name){
        return service.deleteUser(name);
    }

    /**
     * 改
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result<String> update(User user){
        return service.insertUser(user);
    }
}
