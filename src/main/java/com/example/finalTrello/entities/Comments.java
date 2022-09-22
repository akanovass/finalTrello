package com.example.finalTrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tasks task;
}
