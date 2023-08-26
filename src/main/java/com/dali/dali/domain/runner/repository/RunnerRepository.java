package com.dali.dali.domain.runner.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
    boolean existsByCommunityIdAndParticipation(Long community_id, int participation);
    List<Runner> findByCommunityIdAndParticipation(Long community_id, int participation);
    Optional<Runner> findByCommunity(Community community);
}
