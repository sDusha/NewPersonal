package com.example.newpersonal.repositories;

import com.example.newpersonal.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TeamRepository extends JpaRepository<Team, Long>{
    List<Team> findByOwner(User user);
    List<Team> findTeamByUsersTeamContains(User user);
}
