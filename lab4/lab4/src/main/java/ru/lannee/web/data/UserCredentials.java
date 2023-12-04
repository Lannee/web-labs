package ru.lannee.web.data;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCredentials {
    private String name;
    private String token;
}
