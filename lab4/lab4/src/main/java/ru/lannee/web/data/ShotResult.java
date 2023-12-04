package ru.lannee.web.data;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShotResult {
    private double x;
    private double y;
    private double r;
    private boolean hitFact;

    public ShotResult(Shot shot, boolean hitFact) {
        this(shot.getX(), shot.getY(), shot.getR(), hitFact);
    }
}
