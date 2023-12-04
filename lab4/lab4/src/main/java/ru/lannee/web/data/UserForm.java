package ru.lannee.web.data;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {

    private String login;

    private String email;

    private String password;

    public UserForm encoded(PasswordEncoder encoder) {
        return new UserForm(login, email, encoder.encode(password));
    }
}
