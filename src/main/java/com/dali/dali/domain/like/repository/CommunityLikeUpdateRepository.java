package com.dali.dali.domain.like.repository;


import com.dali.dali.domain.like.entity.CommunityLike;

public interface CommunityLikeUpdateRepository {
    void incrementLikeCommunity(Long community_id, CommunityLike communityLike);
    void decrementLikeCommunity(Long community_id, CommunityLike communityLike);
}
