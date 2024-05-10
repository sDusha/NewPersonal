package com.example.newpersonal.services;


import com.example.newpersonal.models.Task;
import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import com.example.newpersonal.models.enums.TaskStatus;
import com.example.newpersonal.repositories.TaskRepository;
import com.example.newpersonal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> listTasks(User user){
        if (user != null) return taskRepository.findByOwner(user);
        return null;
    }

    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    public void saveTask(Principal principal, Task task){
        System.out.println("SaveTask Service");
        task.setOwner(getUserByPrincipal(principal));
        System.out.println(task);
        log.info("Saving new {}", task);
        taskRepository.save(task);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getTasksByTeam(Team group) {
        return taskRepository.findByTeam(group);
    }

    public void statusUp(Task task) {
        TaskStatus[] values = TaskStatus.values();
        int status = task.getStatus().ordinal() + 1;
        if (status >= values.length){
            System.out.println("Уже последняя стадия");
        }
        else{
            System.out.println("status изменен на " + values[status].toString());
            //System.out.println(values[status]);
            //System.out.println(task);
            task.setStatus(values[status]);
            //System.out.println(task);
            taskRepository.save(task);
        }
    }

    public void statusDown(Task task) {
        TaskStatus[] values = TaskStatus.values();
        int status = task.getStatus().ordinal() - 1;
        if (status >= values.length){
            System.out.println("Нет стадии ниже 1");
        }
        else{
            System.out.println("status изменен на" + values[status].toString());
            task.setStatus(values[status]);
            taskRepository.save(task);
        }
    }

}
