package ru.lannee.web.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {

    private String login;

    private String email;

    private String password;
}
