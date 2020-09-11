package com.mengxugu2.demo.mapper;


import com.mengxugu2.demo.entities.User;

import java.util.List;

/**
 * @Auther: 梦学谷
 */
//@Mapper 或 @MapperScan("com.mengxuegu.springboot.mapper")
public interface UserMapper {

    User getUserByUsername(String username);

    List<User> getUsers(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);

}
