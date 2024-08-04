package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Collection;
import java.util.List;

public interface UserService {


    User findByUsername(String username);

    List<User> readAllUsers();

    User readUserById(Long id);

    void saveUser(User user, List<String> roles);

    void updateUser(User user, List<String> roles);

    void deleteUser(Long id);

}
