package com.dali.dali.domain.runner.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RunnerRepository extends JpaRepository<Runner, Long> {
    boolean existsByCommunityIdAndParticipation(Long community_id, int participation);
    List<Runner> findByCommunityIdAndParticipation(Long community_id, int participation);
    Optional<Runner> findByUserAndCommunity(User user, Community community);
    Optional<Runner> findByIdAndParticipation(Long runner_id, int participation);
    // 마이페이지에서 사용자가 작성한 글 가져오기 위해 추가(참여 확인을 하기 위함)
    List<Runner> findByUser(User user);
}