package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import java.util.List;

@Component
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}
