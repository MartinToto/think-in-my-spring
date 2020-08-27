package service;

import entity.User;

import java.util.List;

public interface UserService {
    List<User> listAllUser();

    Integer saveUser(User user);

    Integer countUser();
}
