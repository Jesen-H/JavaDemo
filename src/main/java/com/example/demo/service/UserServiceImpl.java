package com.example.demo.service;

import com.example.demo.bean.Register;
import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result<Register> login(Register register) {
        Result result = new Result<>();
        String user = register.getUsername();
        String psw = register.getPassword();
        result.setResult("fail");
        result.setCode(1);
        if (isEmpty(user)) {
            result.setMsg("账户名不能为空！");
            return result;
        } else if (isEmpty(psw)) {
            result.setMsg("密码不能为空！");
            return result;
        }
        Register login = userMapper.login(register.getUsername());
        if (login == null){
            result.setMsg("账户名不存在！");
            return result;
        }
        if (isEmpty(login.getUsername())) {
            result.setMsg("账户名不存在！");
            return result;
        } else if (!login.getPassword().equals(psw)) {
            result.setMsg("密码错误");
            return result;
        }
        result.setMsg("登录成功");
        result.setCode(200);
        result.setResult(login);
        return result;
    }

    @Override
    public Result<String> register(Register register) {
        Result<String> result = new Result<>();
        result.setCode(1);
        result.setMsg("注册失败");
        result.setResult("fail");
        if (register == null) {
            return result;
        }
        String user = register.getUsername();
        String psw = register.getPassword();
        if (isEmpty(user)) {
            result.setMsg("账户名不能为空！");
            return result;
        } else if (isEmpty(psw)) {
            result.setMsg("密码不能为空！");
            return result;
        }
        Register login = userMapper.login(register.getUsername());
        if (login != null){
            if (login.getUsername().equals(register.getUsername())){
                result.setMsg("该账号已存在");
                return result;
            }
        }
        int i = userMapper.register(register);
        if (i != 1){
            return result;
        }
        result.setMsg("注册成功");
        result.setCode(200);
        result.setResult("success");
        return result;
    }

    @Override
    public Result<User> getInfo(int id) {
        Result result = new Result<>();
        User user = userMapper.getUser(id);
        if (user != null) {
            result.setCode(200);
            result.setMsg("success");
            result.setResult(user);
        } else {
            result.setCode(1);
            result.setMsg("fail");
            result.setResult("");
        }
        return result;
    }

    @Override
    public Result<List<User>> search(String keyword) {
        List<User> info = userMapper.search(keyword);
        Result result = new Result<>();
        if (info == null || info.size() == 0) {
            result.setCode(1);
            result.setMsg("fail");
            result.setResult("");
            return result;
        }
        result.setCode(200);
        result.setMsg("success");
        result.setResult(info);
        return result;
    }

    @Override
    public Result<String> insertUser(User user) {
        Result result = new Result<>();
        result.setCode(1);
        result.setMsg("添加失败");
        result.setResult("");
        if (user != null) {
            if (user.getName() == null || user.getName().isEmpty()) {
                result.setMsg("名称必填");
                return result;
            }
        }
        int add = userMapper.add(user);
        if (add == 1) {
            result.setMsg("添加成功");
            result.setCode(200);
            result.setResult(user);
        }
        return result;
    }

    @Override
    public Result<List<User>> selectUser() {
        List<User> select = userMapper.select();
        Result result = new Result<>();
        if (select == null) {
            result.setCode(1);
            result.setMsg("fail");
            result.setResult("查询失败");
        } else {
            result.setCode(200);
            result.setMsg("success");
            result.setResult(select);
        }
        return result;
    }

    @Override
    public Result<String> deleteUser(String name) {
        Result result = new Result<>();
        result.setCode(1);
        result.setMsg("删除失败");
        result.setResult("");
        if (name == null || name.isEmpty()) {
            return result;
        }
        int delete = userMapper.delete(name);
        if (delete != 1) {
            return result;
        }
        List<User> select = userMapper.select();
        if (select != null || select.size() != 0) {
            result.setCode(200);
            result.setMsg("删除成功");
            result.setResult(select);
        } else {
            result.setMsg("查询失败");
            result.setResult("");
        }
        return result;
    }

    @Override
    public Result<String> updateUser(User user) {
        Result result = new Result<>();
        result.setCode(1);
        result.setMsg("更新失败");
        int update = userMapper.update(user);
        if (update != 1) {
            result.setResult("");
            return result;
        }
        result.setMsg("更新成功");
        result.setCode(200);
        return result;
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
