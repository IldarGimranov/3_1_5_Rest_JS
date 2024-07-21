package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService{


    User findByUsername(String name);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);

    List<User> readAllUsers();

    User readUserById(Long id);

    void saveUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

}
