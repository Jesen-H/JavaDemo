package com.example.demo.controller;

import com.example.demo.bean.Register;
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

    @GetMapping("user_info")
    public Result<User> info(int id) {
        return service.getInfo(id);
    }

    @GetMapping("search")
    public Result<List<User>> search(String keyword) {
        return service.search(keyword);
    }

    /**
     * 登录
     * @param register
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<Register> login(Register register){
        return service.login(register);
    }

    /**
     * 注册账户
     * @param register
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(Register register){
        return service.register(register);
    }
    /**
     * 增
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<String> insertUser(User user) {
        return service.insertUser(user);
    }

    /**
     * 查
     *
     * @return
     */
    @GetMapping("/select")
    public Result<List<User>> selectUser() {
        return service.selectUser();
    }

    /**
     * 删
     *
     * @param name
     * @return
     */
    @PostMapping("/delete")
    public Result<String> delete(String name) {
        return service.deleteUser(name);
    }

    /**
     * 改
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result<String> update(User user) {
        return service.updateUser(user);
    }
}
