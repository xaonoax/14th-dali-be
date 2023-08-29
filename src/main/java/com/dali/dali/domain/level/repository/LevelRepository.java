package com.dali.dali.domain.level.repository;

import com.dali.dali.domain.level.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByRunnerId(Long runner_id);
}
