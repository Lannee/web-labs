package ru.lannee.web.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.entity.User;
import ru.lannee.web.managers.auth.AuthValidationResult;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.JwtUtils;

@Service
public class AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(PasswordEncoder encoder, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public AuthValidationResult createUser(UserForm userForm){
        if(userRepository.getUserByLogin(userForm.getLogin()).isPresent()) return AuthValidationResult.USER_ALREADY_EXIST;
        if(userRepository.findByEmail(userForm.getEmail())!=null) return AuthValidationResult.USER_ALREADY_EXIST;
        User user = new User(userForm.encoded(encoder));
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
//        log.info("Saving new user with email: {}", user.getEmail());
        userRepository.save(user);
        return AuthValidationResult.OK;
    }
//    public AuthorizedUserCredentials loginUser(UserCredentials userCredentials) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userCredentials.getName(), userCredentials.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwtToken = jwtUtils.generateJwtToken(userCredentials.getName());
//        return new AuthorizedUserCredentials(userCredentials.getName(), jwtToken);
//    }
}
