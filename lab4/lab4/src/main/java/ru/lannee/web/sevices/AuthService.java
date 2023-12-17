package ru.lannee.web.sevices;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lannee.web.data.UserCredentials;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.entity.User;
import ru.lannee.web.exceptions.DoesNotExistException;
import ru.lannee.web.exceptions.WrongPasswordException;
import ru.lannee.web.managers.auth.AuthValidationResult;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserCredentials login(UserForm req) throws DoesNotExistException, WrongPasswordException {
        User user = userRepository.getUserByLogin(req.getLogin()).orElseThrow(() -> new DoesNotExistException(req.getLogin()));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new WrongPasswordException(req.getLogin());

        return new UserCredentials(user.getLogin(), jwtUtils.generateJwtToken(user.getLogin()));
    }

    public long getUserIdFromToken(String token) throws DoesNotExistException {
        Claims userClaims = jwtUtils.getClaims(token);
        final String username = userClaims.get("sub", String.class);

        if (!userRepository.existsByLogin(username)) {
            throw new DoesNotExistException(username);
        }

        User userEntity = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new DoesNotExistException(username));
        return userEntity.getId();
    }

    public AuthValidationResult register(UserForm userForm) {
        if(userRepository.existsByLogin(userForm.getLogin())) return AuthValidationResult.USER_ALREADY_EXIST;
        System.out.println(userForm);
        User user = User.builder()
                .login(userForm.getLogin())
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .build();
        System.out.println(user);

        userRepository.save(user);
        return AuthValidationResult.OK;
    }
}
