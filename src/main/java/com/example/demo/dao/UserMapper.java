package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user_info WHERE id=#{id}")
    public User getUser(int id);

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    @Select("SELECT * FROM user_info WHERE concat(name,age) LIKE CONCAT('%',#{keyword},'%')")
    public List<User> search(String keyword);

    /**
     * 插入数据
     * @param user
     * @return
     */
    @Insert("INSERT INTO user_info(name,age,birthday,sex) VALUES(#{name},#{age},#{birthday},#{sex})")
    public int add(User user);

    /**
     * 删除数据
     * @param name
     * @return
     */
    @Delete("DELETE FROM user_info WHERE name=#{name}")
    public int delete(String name);

    /**
     * 更新数据
     * @param user
     * @return
     */
    @Update("UPDATE user_info SET name=#{name},age=#{age},birthday=#{birthday},sex=#{sex} WHERE id=#{id}")
    public int update(User user);

    /**
     * 查询数据
      * @return
     */
    @Select("SELECT * FROM user_info")
    public List<User> select();
}
