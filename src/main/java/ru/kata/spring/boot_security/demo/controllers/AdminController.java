package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.readUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> readAllUsers() {
        return new ResponseEntity<>(userService.readAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user, List<String> roles) {
        userService.saveUser(user, roles);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, List<String> roles
            , @PathVariable("id") Long id) {
        userService.updateUser(user, roles);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @GetMapping
//    public String readAllUsers(Principal principal, Model model) {
//        model.addAttribute("users", userService.readAllUsers());
//        model.addAttribute("admin", userService.findByUsername(principal.getName()));
//        return "admin_page";
//    }

//    @PostMapping("/createuser")
//    public String createUser(@ModelAttribute("user") @Valid User user,
//                             BindingResult bindingResult, @RequestParam("role") List<String> roles) {
//        if (bindingResult.hasErrors()) {
//            return "admin_page";
//        }
//        userService.saveUser(user, roles);
//        return "redirect:/admin";
//    }

//    @PostMapping("/deleteuser")
//    public String deleteUser(@RequestParam("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }

//    @PostMapping("/updateuser")
//    public String updateUser(@ModelAttribute("user") @Valid User user,
//                             BindingResult bindingResult,
//                             @RequestParam("role") List<String> roles,
//                             @RequestParam("id") Long id) {
//        if (bindingResult.hasErrors()) {
//            return "admin_page";
//        }
//        userService.updateUser(user, roles);
//        return "redirect:/admin";
//    }
}
