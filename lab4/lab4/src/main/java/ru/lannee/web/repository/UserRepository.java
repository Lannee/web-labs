package ru.lannee.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lannee.web.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
    Optional<User> getUserByLogin(String name);
    User findByEmail(String email);
}