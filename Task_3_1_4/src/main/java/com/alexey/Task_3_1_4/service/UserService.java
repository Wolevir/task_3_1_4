package com.alexey.Task_3_1_4.service;

import com.alexey.Task_3_1_4.model.Role;
import com.alexey.Task_3_1_4.model.User;
import com.alexey.Task_3_1_4.repository.RoleRepository;
import com.alexey.Task_3_1_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    public User getUserByEmail(String email) {

        TypedQuery<User> user = em.createQuery(
                "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :emailParam", User.class);
        user.setParameter("emailParam", email);
        return user.getSingleResult();
    }

    @Transactional
    public User getUserById(long id) {
        return userRepository.findById(id).get(); //eager operation
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    
    public void setInitData() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
        userRepository.save(new User("user", "user", (byte) 30, "user@mail.ru", "user", new HashSet<Role>() {{
            add(userRole);
        }}));
        userRepository.save(new User("admin", "admin", (byte) 35, "admin@mail.ru", "admin", new HashSet<Role>() {{
            add(userRole);
            add(adminRole);
        }}));
    }
}
