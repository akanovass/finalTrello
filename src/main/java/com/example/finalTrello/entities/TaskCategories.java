package com.example.finalTrello.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="taskCategories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;


}
