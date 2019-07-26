package com.example.demo.service;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;

import java.util.List;

public interface UserService {

    Result<User> getInfo(int id);

    Result<List<User>> search(String keyword);

    Result<String> insertUser(User user);

    Result<List<User>> selectUser();

    Result<String> deleteUser(String name);

    Result<String> updateUser(User user);

}
