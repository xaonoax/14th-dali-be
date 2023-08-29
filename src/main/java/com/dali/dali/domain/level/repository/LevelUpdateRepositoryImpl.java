package com.dali.dali.domain.level.repository;

import com.dali.dali.domain.level.entity.Level;
import com.dali.dali.domain.level.entity.QLevel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LevelUpdateRepositoryImpl implements LevelUpdateRepository {

    private final JPAQueryFactory jq;

    @Override
    public void updateLevel(Level level) {
        QLevel qLevel = QLevel.level;
        jq.update(qLevel)
                .set(qLevel.levelName, qLevel.levelName.add(1))
                .where(qLevel.eq(level))
                .execute();
    }
}
