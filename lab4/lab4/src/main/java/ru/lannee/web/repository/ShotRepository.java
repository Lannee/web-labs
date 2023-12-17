package ru.lannee.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lannee.web.entity.Result;

import java.util.List;

@Repository
public interface ShotRepository extends JpaRepository<Result, Long> {
    List<Result> findByUser_id(Long id);

//    @Modifying
//    @Query("DELETE FROM 'results' e WHERE e.user_id = :user_id")
//    void deleteByUserId(@Param("user_id") Long user_id);

    void deleteByUser_id(Long user_id);
}
