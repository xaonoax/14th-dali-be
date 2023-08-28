package com.dali.dali.domain.runner.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RunRepositoryImpl implements RunRepository {

    private final JPAQueryFactory jq;

    @Override
    public void addRunner(Community community) {

    }

    @Override
    public void deleteRunner(Community community) {

    }

    @Override
    public void confirmRunner(Runner runner) {

    }

//    @Override
//    public void addRunner(Community community) {
//        QCommunity qCommunity = QCommunity.community;
//        jq.update(qCommunity)
//                .set(qCommunity.currentCount, qCommunity.currentCount.add(1))
//                .where(qCommunity.eq(community))
//                .execute();
//    }
//
//    @Override
//    public void deleteRunner(Community community) {
//        QCommunity qCommunity = QCommunity.community;
//        jq.update(qCommunity)
//                .set(qCommunity.currentCount, qCommunity.currentCount.subtract(1))
//                .where(qCommunity.eq(community))
//                .execute();
//    }
//
//    @Override
//    public void confirmRunner(Runner runner) {
//        QRunner qRunner = QRunner.runner;
//        jq.update(qRunner)
//                .set(qRunner.participation, qRunner.participation.add(1))
//                .where(qRunner.eq(runner))
//                .execute();
//    }
}