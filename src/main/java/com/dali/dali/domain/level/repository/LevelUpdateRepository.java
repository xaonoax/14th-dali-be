package com.dali.dali.domain.level.repository;

import com.dali.dali.domain.level.entity.Level;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelUpdateRepository {
    void updateLevel(Level level);
}
