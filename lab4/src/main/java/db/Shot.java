package db;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shots")
public class Shot {

    @Id
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
