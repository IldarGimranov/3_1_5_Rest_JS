package ru.kata.spring.boot_security.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String readUser(Principal principal, Model model) {
        //User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "user";
    }

    @GetMapping
    public String index() {return "index";}












//    @GetMapping
//    public String index(ModelMap model) {
//        model.addAttribute("user", userService.findAllUsers());
//        return "index";
//    }
//
//    @GetMapping("/new")
//    public String newUser(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "posting";
//    }
//
//    @PostMapping
//    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "posting";
//        }
//        userService.saveUser(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/edit")
//    public String editUser(@RequestParam("id") Long id, ModelMap model) {
//        model.addAttribute(userService.readUser(id));
//        return "edit";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @RequestParam("id") Long id) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//        userService.updateUser(id, user);
//        return "redirect:/users";
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam(value = "id", required = false) Long id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
}