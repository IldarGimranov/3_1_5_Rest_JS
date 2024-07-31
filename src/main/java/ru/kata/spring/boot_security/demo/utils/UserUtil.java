package ru.kata.spring.boot_security.demo.utils;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Collection;
import java.util.stream.Collectors;

public final class UserUtil {
    public static String getStringRoles(Collection<Role> roles) {
        return roles.stream()
                .map(role -> role.getAuthority().replaceAll("ROLE_", ""))
                .collect(Collectors.joining(", "));
    }
}
