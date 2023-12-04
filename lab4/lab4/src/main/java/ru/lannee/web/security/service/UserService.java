package ru.lannee.web.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lannee.web.entity.User;
import ru.lannee.web.repository.ShotRepository;
import ru.lannee.web.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username);

        if(user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

//    public boolean saveUser(User user) {
//        User existingUser = userRepository.findUserByLogin(user.getLogin());
//
//        if(existingUser != null) return false;
//
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
}
