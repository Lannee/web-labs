package ru.lannee.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shots")
public class Shot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "r")
    private Double r;

    @Column(name = "hit")
    private boolean hit;

    @Column(name = "currTime")
    private LocalDateTime currTime;

    @Column(name = "execTime")
    private long execTime;
}
