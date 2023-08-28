package com.dali.dali.domain.runner.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
    boolean existsByCommunityIdAndParticipation(Long community_id, int participation);
    List<Runner> findByCommunityIdAndParticipation(Long community_id, int participation);
    Optional<Runner> findByUserAndCommunity(User user, Community community);
}
