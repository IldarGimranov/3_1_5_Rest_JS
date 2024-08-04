package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String readAllUsers(Principal principal, Model model) {
        model.addAttribute("users", userService.readAllUsers());
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
        return "admin_page";
    }

    @PostMapping("/createuser")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @RequestParam("role") List<String> roles) {
        if (bindingResult.hasErrors()) {
            return "admin_page";
        }
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("role")List<String> roles,
                             @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "admin_page";
        }
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }
}
