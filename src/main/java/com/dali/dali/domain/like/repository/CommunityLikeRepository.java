package com.dali.dali.domain.like.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.like.entity.CommunityLike;
import com.dali.dali.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    Optional<CommunityLike> findByUserAndCommunity(User user, Community community);
}
