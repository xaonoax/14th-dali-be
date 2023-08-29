package com.dali.dali.domain.users.repository;

import com.dali.dali.domain.users.entity.QUser;
import com.dali.dali.domain.users.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserLevelRepositoryImpl implements UserLevelRepository {

    private final JPAQueryFactory jq;

    @Override
    public void updateLevel(User user) {
        QUser qUser = QUser.user;
        jq.update(qUser)
                .set(qUser.level, qUser.level.add(1))
                .where(qUser.eq(user))
                .execute();
    }
}
