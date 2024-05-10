package com.example.newpersonal.repositories;

import com.example.newpersonal.models.Task;
import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwner(User user);

    List<Task> findByTeam(Team group);
}
