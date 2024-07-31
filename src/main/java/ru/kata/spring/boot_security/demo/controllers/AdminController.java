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
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String readAllUsers(Principal principal, Model model) {
        model.addAttribute("users", userService.readAllUsers());
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
        model.addAttribute("authorities", roleService.findAll());
        return "admin_page";
    }

//    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.findAll());
//        return "create";
//    }

    @PostMapping("/createuser")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @RequestParam("role") String selectedRole) {
        if (bindingResult.hasErrors()) {
            return "admin_page";
        }
        if (selectedRole.equals("ROLE_USER")) {
            user.setAuthorities(roleService.findByUsername("ROLE_USER"));
        } else if (selectedRole.equals("ROLE_ADMIN")) {
            user.setAuthorities(roleService.findAll());
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/delete")
//    public String delete(Model model, @RequestParam("id") Long id) {
//        model.addAttribute(userService.readUserById(id));
//        return "delete";
//    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/update")
//    public String update(Model model,
//                         @RequestParam("id") Long id) {
//        model.addAttribute(userService.readUserById(id));
//        return "update";
//    }

    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("role") String selectedRole,
                             @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "admin_page";
        }
        if (selectedRole.equals("ROLE_USER")) {
            user.setAuthorities(roleService.findByUsername("ROLE_USER"));
        } else if (selectedRole.equals("ROLE_ADMIN")) {
            user.setAuthorities(roleService.findAll());
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
}
