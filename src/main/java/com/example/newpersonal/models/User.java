package com.example.newpersonal.models;

import com.example.newpersonal.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private boolean active;
    @Column(length = 1000)
    private String password;
    @ManyToMany(mappedBy = "usersTeam")
    private Set<Team> teams = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER )
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreate;

    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }
    public boolean isOwner(){
        return roles.contains(Role.ROLE_OWNER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}