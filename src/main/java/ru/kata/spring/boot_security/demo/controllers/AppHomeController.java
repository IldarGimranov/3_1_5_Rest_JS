package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppHomeController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "userInfo";
    }

}
