package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {
    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(Long userId);
}
