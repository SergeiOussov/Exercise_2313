package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {
    public User findUserById(Long userId);

    public List<User> allUsers();

    public boolean saveUser(User user);

    public boolean updateUser(User user);


    public boolean deleteUserById(Long userId);
}
