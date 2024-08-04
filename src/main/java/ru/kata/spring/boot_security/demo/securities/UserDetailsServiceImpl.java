package ru.kata.spring.boot_security.demo.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //возвращаем по запросу имя пользователя по пришедшемму имени(перегоняем наших юзеров юзерам которые понимает спринг)
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found exception"); //если такой пользователь не нашелся
        }
        return user;
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> authorities) {
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role.getUsername())).collect(Collectors.toList());
    }
}
