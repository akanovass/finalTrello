package com.example.finalTrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="folders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<TaskCategories> categories;
}
