package ru.lannee.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lannee.web.entity.Shot;

@Repository
public interface ShotRepository extends JpaRepository<Shot, Long> {
}
