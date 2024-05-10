package com.example.newpersonal.services;

import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import com.example.newpersonal.models.enums.Role;
import com.example.newpersonal.repositories.TeamRepository;
import com.example.newpersonal.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public List<Team> listGroupsByOwner(User user){
        if (user != null) return teamRepository.findByOwner(user);
        return null;
    }

    public void deleteGroup(Long id) {
        teamRepository.deleteById(id);
    }

    public void saveGroup(Principal principal, Team team) {
        team.setOwner(getUserByPrincipal(principal));
        log.info("Saving new {}", team);
        teamRepository.save(team);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public Team getGroupById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public List<Team> listAllGroups() {
        return teamRepository.findAll();
    }

    @Transactional
    public void addUser(Team team, Map<String, String> form) {
        for (String key : form.keySet()){
            if (key.equals("status")){
                long id = Long.parseLong(form.get("status"), 10);
                User user = userRepository.findUserById(id);

                System.out.println(team);
                System.out.println(user);

                System.out.println(team.getUsersTeam());
                team.getUsersTeam().add(user);
                System.out.println(team.getUsersTeam());

                teamRepository.save(team);
                userRepository.save(user);
            }
        }
    }

    @Transactional
    public void deleteUser(Team team, Map<String, String> form) {
        for (String key : form.keySet()){
            if (key.equals("statusDelete")) {
                long id = Long.parseLong(form.get("statusDelete"), 10);
                User user = userRepository.findUserById(id);

                System.out.println(team);
                System.out.println(user);

                System.out.println(team.getUsersTeam());
                team.getUsersTeam().remove(user);
                System.out.println(team.getUsersTeam());

                teamRepository.save(team);
                userRepository.save(user);
            }
        }
    }

    public List<User> findUsersTeam(Team team) {
        return userRepository.findUserByTeamsContains(team);
    }

    public Object listGroupsByUser(User user) {
        return teamRepository.findTeamByUsersTeamContains(user);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }





    /*
    public Object getUsersByTeam(Team team) {
        // должно возвращать список пользователей из определенной команды
        return teamRepository.findByUsersTeam(team);
    }

     */
}
