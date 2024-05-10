package com.example.newpersonal.controllers;


import com.example.newpersonal.models.Task;
import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;

import com.example.newpersonal.services.TaskService;
import com.example.newpersonal.services.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TeamService teamService;

    @GetMapping(value = "/task/{id}")
    public String taskInfo(@PathVariable Long id, Principal principal, Model model){
        User user = teamService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);

        Team group = task.getTeam();
        model.addAttribute("group", group);

        List<User> users = teamService.findUsersTeam(group);
        model.addAttribute("users", users);

        boolean check = users.contains(user);
        model.addAttribute("check", check);
        return "taskInfo";
    }

    @GetMapping(value={"/task/{id}/create"})
    public String taskCreateForm(@PathVariable Long id, Principal principal, Model model){
        //System.out.println(123);
        User user = teamService.getUserByPrincipal(principal);
        //System.out.println(user);
        Team team = teamService.getGroupById(id);
        model.addAttribute("user", user);
        model.addAttribute("group", team);
        //System.out.println(team);
        return "taskCreate";
    }

    @PostMapping(value={"/task/{id}/create"})
    public String taskCreate(Principal principal, Task task){
        //System.out.println("SaveTask Controller");
        taskService.saveTask(principal, task);
        String owner = task.getTeam().getId().toString() ;
        return "redirect:/group/" + owner;
    }

    @GetMapping(value={"/task/{id}/status/up"})
    public String statusUp(@PathVariable Long id, HttpServletRequest request){
        Task task = taskService.getTaskById(id);
        taskService.statusUp(task);


        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            // В случае, если URL страницы, с которой был отправлен запрос, неизвестен,
            // можно сделать перенаправление на другую страницу
            return "redirect:/groups";
        }
    }

    @GetMapping(value={"/task/{id}/status/down"})
    public String statusDown(@PathVariable Long id, HttpServletRequest request){
        Task task = taskService.getTaskById(id);
        taskService.statusDown(task);


        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            // В случае, если URL страницы, с которой был отправлен запрос, неизвестен,
            // можно сделать перенаправление на другую страницу
            return "redirect:/groups";
        }
    }
}
