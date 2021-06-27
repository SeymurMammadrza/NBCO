package com.app.nbco.user.controller;

import com.app.nbco.role.entity.Role;
import com.app.nbco.role.repository.RoleRepository;
import com.app.nbco.user.entity.User;
import com.app.nbco.user.service.SecurityService;
import com.app.nbco.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validator.UserValidator;

import javax.transaction.Transactional;
import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Controller
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleRepository roleRepository;
    private final SecurityService securityService;

    @GetMapping("/list")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/new")
    public String newUser(Model model, User user) {
        Set<Role> rolesList = new HashSet<>();
        roleRepository.findAll().forEach(rolesList::add);
        model.addAttribute("user", user);
        model.addAttribute("rolesList", rolesList);
        return "user/userOperations";
    }

    @PostMapping(value = "/save")
    @Transactional
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

        System.out.println(user);
        if (user.getId() == null) {
            if (userService.findByUsername(user.getUsername()) != null) {
                userValidator.validate(user, bindingResult);
            }

        } else {

            if (!(user.getPassword().equals(user.getPasswordConfirm()))) {
                userValidator.validate(user, bindingResult);
                if (bindingResult.hasErrors()) {
                    return "user/userCreationFailed";
                }
            }
        }
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/api/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        System.out.println("user deleted");
        return "redirect:/api/user/list";
    }
}

