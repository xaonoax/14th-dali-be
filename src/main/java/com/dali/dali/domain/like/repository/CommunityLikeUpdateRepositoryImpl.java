package com.dali.dali.domain.like.repository;

import com.dali.dali.domain.like.entity.CommunityLike;
import com.dali.dali.domain.like.entity.QCommunityLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommunityLikeUpdateRepositoryImpl implements CommunityLikeUpdateRepository{
    private final JPAQueryFactory jq;

    @Override
    public void incrementLikeCommunity(Long community_id, CommunityLike communityLike) {
        QCommunityLike qCommunityLike = QCommunityLike.communityLike;
        jq.update(qCommunityLike)
                .set(qCommunityLike.community, communityLike.getCommunity())
                .set(qCommunityLike.user, communityLike.getUser())
                .where(qCommunityLike.eq(communityLike))
                .execute();
    }

    @Override
    public void decrementLikeCommunity(Long community_id, CommunityLike communityLike) {
        QCommunityLike qCommunityLike = QCommunityLike.communityLike;
        jq.delete(qCommunityLike)
                .where(qCommunityLike.community.eq(communityLike.getCommunity())
                        .and(qCommunityLike.user.eq(communityLike.getUser())))
                .execute();
    }
}
