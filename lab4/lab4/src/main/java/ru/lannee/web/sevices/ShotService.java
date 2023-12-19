package ru.lannee.web.sevices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lannee.web.data.Shot;
import ru.lannee.web.data.ShotResult;
import ru.lannee.web.entity.Result;
import ru.lannee.web.entity.User;
import ru.lannee.web.exceptions.InvalidJWTTokenException;
import ru.lannee.web.managers.HitManager;
import ru.lannee.web.repository.ShotRepository;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.JwtUtils;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShotService {
    private final ShotRepository shotRepository;
    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    public List<Result> listAttempt(Long id) {
        return shotRepository.findAll();
    }

    @Transactional
    public Result save(Shot shot) throws InvalidJWTTokenException {
        long start = System.nanoTime();
        System.out.println("shot : " + shot);
        boolean isHit = HitManager.checkHit(shot.getX(), shot.getY(), shot.getR());
        System.out.println("Hit fact: " + isHit);

        String login = jwtUtils.getUserNameFromJwtToken(shot.getToken());
        System.out.println("login: " + login);
        Optional<User> user = userRepository.getUserByLogin(login);

        if(user.isEmpty()) throw new InvalidJWTTokenException();

        Result result = new Result(shot.getX(),
                shot.getY(),
                shot.getR(),
                isHit,
                LocalDateTime.now(),
                System.nanoTime() - start,
                user.get());
        shotRepository.save(result);

        return result;
    }

    @Transactional
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Transactional
    public List<Result> findAllByUserLogin(String login) {
        Optional<User> user = userRepository.getUserByLogin(login);
        return user.map(
                value -> shotRepository
                        .findByUser_id(value.getId()))
                .orElse(null);
    }



    @Transactional
    public void deleteAttempt(Long id) {
        System.out.println("Delete "+id);
        shotRepository.deleteByUser_id(id);
    }

    @Transactional
    public boolean clear(String login) {
        Optional<User> user = userRepository.getUserByLogin(login);
        if(user.isPresent()) {
            shotRepository.deleteByUser_id(user.get().getId());
            return true;
        }
        return false;
    }

    public Result getAttemptById(Long id) {
        return shotRepository.findById(id).orElse(null);
    }
}