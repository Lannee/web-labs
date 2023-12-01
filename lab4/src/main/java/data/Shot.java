package data;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@Entity
@Table(name = "results")
@SessionScoped
public class Shot implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Shot() {}

    public Shot(long id, Double xCoord, Double yCoord, Double radius, boolean hitFact, LocalDateTime currTime, long execTime) {
        this.id = id;
        this.x = xCoord;
        this.y = yCoord;
        this.r = radius;
        this.hit = hitFact;
        this.currTime = currTime;
        this.execTime = execTime;
    }

    public long getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public LocalDateTime getCurrTime() {
        return currTime;
    }

    public long getExecTime() {
        return execTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setCurrTime(LocalDateTime currTime) {
        this.currTime = currTime;
    }

    public void setExecTime(long execTime) {
        this.execTime = execTime;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "xCoord=" + x +
                ", yCoord=" + y +
                ", radius=" + r +
                ", hitFact=" + hit +
                ", currTime=" + currTime +
                ", execTime=" + execTime +
                '}';
    }
}
