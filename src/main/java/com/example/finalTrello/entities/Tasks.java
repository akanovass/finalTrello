package com.example.finalTrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private int status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Folders folder;
}
