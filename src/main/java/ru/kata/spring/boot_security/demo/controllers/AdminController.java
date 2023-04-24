package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;
import java.util.List;

@Controller
public class AdminController {
    private final UserServiceImp userServiceImp;

    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("userList", userServiceImp.allUsers());
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUserEditPage(@PathVariable("id") Long id, ModelMap model) {
        User user = userServiceImp.findUserById(id);
        model.addAttribute("addnew", false);
        model.addAttribute("user", user);
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "useredit";
    }

    @DeleteMapping("/admin/edit/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String showRegistrationPage(Model model) {
        model.addAttribute("addnew", true);
        model.addAttribute("user", new User());
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "useredit";
    }

    @PostMapping("/admin/new")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (!userServiceImp.saveUser(user)){
            model.addAttribute("userExistsError", true);
            List<Role> roles = (List<Role>) roleRepository.findAll();
            model.addAttribute("allRoles", roles);
            model.addAttribute("addnew", true);
            return "useredit";
        }
        return "redirect:/admin";
    }

    @PutMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        if (!userServiceImp.updateUser(user)){
            model.addAttribute("userExistsError", true);
            List<Role> roles = (List<Role>) roleRepository.findAll();
            model.addAttribute("allRoles", roles);
            model.addAttribute("addnew", false);
            return "useredit";
        }
        return "redirect:/admin";
    }
}
