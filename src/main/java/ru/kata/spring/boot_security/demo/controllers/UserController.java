package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserDetailsImp;

@Controller
public class UserController {

    @GetMapping(value = {"/user"})
    public String  showUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        model.addAttribute("user", userDetailsImp.getUser());
        return "userhome";
    }
}
