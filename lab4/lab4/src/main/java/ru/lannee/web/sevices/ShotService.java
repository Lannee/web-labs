package ru.lannee.web.sevices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lannee.web.data.Shot;
import ru.lannee.web.data.ShotResult;
import ru.lannee.web.entity.Result;
import ru.lannee.web.entity.User;
import ru.lannee.web.managers.HitManager;
import ru.lannee.web.repository.ShotRepository;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.JwtUtils;

import java.security.Principal;
import java.util.List;
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
    public ShotResult save(Shot shot) {
        boolean isHit = HitManager.checkHit(shot.getX(), shot.getY(), shot.getR());
        ShotResult hitResult = new ShotResult(shot, isHit);

        Result result = new Result(shot.getX(),shot.getY(),shot.getR(), isHit, userRepository.getUserByLogin(jwtUtils.getUserNameFromJwtToken(shot.getToken())).get());
        shotRepository.save(result);
        return hitResult;
    }

    @Transactional
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Transactional
    public List<ShotResult> findAllByOwnerId(Long id) {
        return shotRepository.findByUserId(id).stream()
                .map(Result::toShotResult).collect(Collectors.toList());
    }



    @Transactional
    public void deleteAttempt(Long id) {
        System.out.println("Delete "+id);
        shotRepository.deleteByUser_id(id);
    }

    public Result getAttemptById(Long id) {
        return shotRepository.findById(id).orElse(null);
    }
}