package com.example.newpersonal.models;

import com.example.newpersonal.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.example.newpersonal.models.enums.TaskStatus.START;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private int level; //???
    private TaskStatus status;
    private LocalDateTime dateOfCreate; // дата постановки задания
    private LocalDateTime deadline; // срок сдачи
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User owner;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Team team;

    @PrePersist
    private void init(){
        status = START;
        dateOfCreate = LocalDateTime.now();
    }
}