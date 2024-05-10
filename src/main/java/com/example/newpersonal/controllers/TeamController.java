package com.example.newpersonal.controllers;

import com.example.newpersonal.models.Task;
import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import com.example.newpersonal.services.TaskService;
import com.example.newpersonal.services.TeamService;
import com.example.newpersonal.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping(value={"/","/groups"})
    public String groups(Principal principal, Model model){
        User user = teamService.getUserByPrincipal(principal);
        List<Team> team = teamService.listAllGroups();
        model.addAttribute("user", user);
        model.addAttribute("teams", team);
        model.addAttribute("userTeams", teamService.listGroupsByUser(user));
        model.addAttribute("ownerTeams", teamService.listGroupsByOwner(user));

        //System.out.println(team);
        return "groups";
    }

    @GetMapping(value={"/group/{id}"})
    public String groupInfo(@PathVariable Long id, Model model, Principal principal){
        Team group = teamService.getGroupById(id);
        //System.out.println(group);
        //System.out.println();
        User user = teamService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        //model.addAttribute("teamUsers", teamService.getUsersByTeam(group));
        model.addAttribute("group", group);

        List<User> users = teamService.findUsersTeam(group);
        model.addAttribute("users", users);

        boolean check = users.contains(user);
        model.addAttribute("check", check);

        List<Task> tasks = taskService.getTasksByTeam(group);
        model.addAttribute("tasks", tasks);

        List<User> personal = userService.list();
        personal.removeAll(users);
        personal.remove(group.getOwner());
        model.addAttribute("personal", personal); // все пользователи возможные для добавления
        return "group_info";
    }


    @GetMapping(value={"/group/create"})
    public String groupCreateForm(Principal principal, Model model){
        User user = teamService.getUserByPrincipal(principal);
        //List<Team> team = teamService.listGroupsByOwner(user);
        model.addAttribute("user", user);
        //model.addAttribute("group", team);
        return "groupCreate";
    }

    @PostMapping(value={"/group/create"})
    public String groupCreate(Principal principal,Team team){
        teamService.saveGroup(principal, team);
        return "redirect:/groups";
    }

    @GetMapping(value={"/group/delete/{id}"})
    public String groupDelete(@PathVariable Long id) throws IOException {
        teamService.deleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/group/{id}/addUser")
    public String addUser(@PathVariable Long id, @RequestParam Map<String, String> form){
        Team team = teamService.getGroupById(id);
        System.out.println(team.getUsersTeam());
        teamService.addUser(team, form);
        return "redirect:/group/" + id.toString();
    }

    @PostMapping("/group/{id}/deleteUser")
    public String deleteUser(@PathVariable Long id, @RequestParam Map<String, String> form) {
        Team team = teamService.getGroupById(id);
        for (String key : form.keySet()) {
            if (key.equals("statusDelete")) {
                User user = userService.findUserById(Long.parseLong(form.get("statusDelete"), 10));

                team.removeUser(user);

                teamService.save(team);
            }
            //teamService.deleteUser(team, form);
        }
        return "redirect:/group/" + id.toString();
    }
}

