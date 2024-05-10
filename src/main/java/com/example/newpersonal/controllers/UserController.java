package com.example.newpersonal.controllers;

import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import com.example.newpersonal.services.TeamService;
import com.example.newpersonal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/registration")
    public String registration(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if (!userService.createUser(user)){
            model.addAttribute("errorMessage", "пользователь с таким emai:"
                    + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @PostMapping("/login")
    public String loginWelcome(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "redirect:/groups";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Principal principal, Model model){
        // отображение навигационной понели с правами того пользователя чей профиль смотрите
        model.addAttribute("currenUser", userService.getUserByPrincipal(principal));
        model.addAttribute("teams", teamService.listGroupsByUser(user));
        model.addAttribute("user", user);
        model.addAttribute("ownerTeams", teamService.listGroupsByOwner(user));
        return "profile";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        //System.out.println(user);
        model.addAttribute("ownerTeams", teamService.listGroupsByOwner(user));
        model.addAttribute("teams", teamService.listGroupsByUser(user));
        model.addAttribute("user", user);
        return "profile";
    }
}
