package com.martin.thinkinmydubbo.service.impl;


import entity.User;
import org.apache.dubbo.config.annotation.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin
 * @description
 * @date 2020/5/5
 */

@Service(timeout = 1000 * 5, retries = 0, version = "1.0")
public class UserServiceImpl implements UserService {
    @Override
    public List<User> listAllUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"martin","122"));
        users.add(new User(2L,"eden","122"));
        return users;
    }

    @Override
    public Integer saveUser(User user) {

        return 1;
    }

    @Override
    public Integer countUser() {

        return 1;
    }
}
