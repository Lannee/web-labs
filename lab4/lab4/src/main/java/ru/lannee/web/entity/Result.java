package ru.lannee.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import ru.lannee.web.data.ShotResult;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(insertable=false, updatable=false)
    private Long user_id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;


    public Result(double x, double y, double r, boolean hit, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.user_id = user.getId();
        this.user = user;
    }

    public ShotResult toShotResult() {
        return new ShotResult(x, y, r, hit);
    }
}
