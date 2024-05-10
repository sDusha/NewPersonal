package com.example.newpersonal.repositories;


import com.example.newpersonal.models.Team;
import com.example.newpersonal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPhoneNumber(String userPhone);

    User findUserById(long id);

    List<User> findUserByTeamsContains(Team team);
}