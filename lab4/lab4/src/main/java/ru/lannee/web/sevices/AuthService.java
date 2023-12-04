package ru.lannee.web.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lannee.web.data.UserCredentials;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.entity.User;
import ru.lannee.web.managers.auth.AuthValidationResult;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.JwtUtils;

import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(PasswordEncoder encoder, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthValidationResult createUser(UserForm userForm){
        if(userRepository.getUserByLogin(userForm.getLogin()).isPresent()) return AuthValidationResult.USER_ALREADY_EXIST;
        if(userRepository.findByEmail(userForm.getEmail())!=null) return AuthValidationResult.USER_ALREADY_EXIST;
//        User user = new User(userForm.encoded(encoder));
        User user = new User(userForm);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
//        log.info("Saving new user with email: {}", user.getEmail());
        userRepository.save(user);
        return AuthValidationResult.OK;
    }

    public UserCredentials loginUser(UserForm userForm) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userForm.getLogin(), userForm.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRepository.getUserByLogin(userForm.getLogin());
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found");
        System.out.println(user.get().getPassword());
        System.out.println(passwordEncoder.encode(userForm.getPassword()));
        if(!user.get().getPassword().equals(passwordEncoder.encode(userForm.getPassword()))) throw new Exception("Invalid password");

        String jwtToken = JwtUtils.generateJwtToken(userForm.getLogin());
        return new UserCredentials(userForm.getLogin(), jwtToken);
    }
}
