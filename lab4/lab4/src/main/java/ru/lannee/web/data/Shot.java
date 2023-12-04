package ru.lannee.web.data;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shot {
    private double x;
    private double y;
    private double r;
    private String token;
}
