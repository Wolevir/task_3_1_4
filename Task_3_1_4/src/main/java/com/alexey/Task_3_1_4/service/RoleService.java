package com.alexey.Task_3_1_4.service;

import com.alexey.Task_3_1_4.model.Role;
import com.alexey.Task_3_1_4.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public Role getRoleById(long id) {
        return roleRepository.getById(id);
    }

    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
