package com.dali.dali.domain.runner.service;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.runner.repository.RunRepository;
import com.dali.dali.domain.runner.repository.RunnerRepository;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserLevelRepository;
import com.dali.dali.domain.users.repository.UserRepository;
import com.dali.dali.global.exception.DuplicateResourceException;
import com.dali.dali.global.exception.NotFoundException;
import com.dali.dali.global.exception.ParticipationAlreadyConfirmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RunnerService {
    private final RunRepository runRepository;
    private final RunnerRepository runnerRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final UserLevelRepository userLevelRepository;

    private User getLoggedInUser(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        String email = principal.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("로그인 한 유저 정보를 찾을 수 없습니다."));
    }

    private Community getCommunityId(Long community_id) {
        return communityRepository.findById(community_id)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));
    }

    public void addRunner(RunnerDto runnerDto, Principal principal) throws Exception {

        User loginUser = getLoggedInUser(principal);
        Community community = getCommunityId(runnerDto.getCommunity_id());

        if (community.getCurrentCount() >= community.getUserCount()) {
            throw new IllegalStateException("참여 마감되었습니다.");
        }

        // 이미 참여하기가 눌린 상태라면 에러 반환
        if (runnerRepository.findByUserAndCommunity(loginUser, community).isPresent()) {
            throw new DuplicateResourceException("해당 글에는 이미 참여하기 되어있는 상태입니다.");
        }

        // 러닝메이트가 종료된 글에 참여하기를 누르면 에러 반환
        if (runnerRepository.existsByCommunityIdAndParticipation(runnerDto.getCommunity_id(), 1)) {
            throw new ParticipationAlreadyConfirmException("이미 달리는 중입니다. 참여할 수 없습니다.");
        }

        // 로그인한 사용자가 해당 커뮤니티의 작성자와 같다면 에러 반환
        if (loginUser.getUserId().equals(community.getUser().getUserId())) {
            throw new IllegalStateException("작성자는 참여할 수 없습니다.");
        }

        Runner runner = Runner.builder()
                .user(loginUser)
                .community(community)
                .build();

        runnerRepository.save(runner);
        runRepository.addRunner(community);
    }

    public void deleteRunner(RunnerDto runnerDto, Principal principal) throws Exception {

        User loginUser = getLoggedInUser(principal);
        Community community = getCommunityId(runnerDto.getCommunity_id());

        Runner runner = runnerRepository.findByUserAndCommunity(loginUser, community)
                .orElseThrow(() -> new NotFoundException("참가하기 정보를 찾을 수 없습니다."));

        if (runnerRepository.existsByCommunityIdAndParticipation(runnerDto.getCommunity_id(), 1)) {
            throw new ParticipationAlreadyConfirmException("참가확인이 완료되어 참가 취소를 할 수 없습니다.");
        }

        runnerRepository.delete(runner);
        runRepository.deleteRunner(community);
    }

    public void confirmRunner(RunnerDto runnerDto, Principal principal) throws Exception {

        User loginUser = getLoggedInUser(principal);
        Community community = getCommunityId(runnerDto.getCommunity_id());

        Optional<Runner> optionalRunner = runnerRepository.findByCommunityId(runnerDto.getCommunity_id());

        if (!optionalRunner.isPresent()) {
            throw new NotFoundException("해당 게시글은 러닝메이트 팀이 존재하지 않습니다.");
        }

        Runner run = optionalRunner.get();

        // 로그인한 사용자가 커뮤니티의 작성자가 아니라면 예외 발생
        if (!loginUser.getUserId().equals(community.getUser().getUserId())) {
            throw new IllegalStateException("작성자만 참여확인을 할 수 있습니다.");
        }

        if (runnerRepository.existsByCommunityIdAndParticipation(run.getCommunity().getId(), 1)) {
            throw new ParticipationAlreadyConfirmException("참가확인이 완료된 러닝메이트입니다.");
        }

        List<Runner> runners = runnerRepository.findByCommunityIdAndParticipation(run.getCommunity().getId(), 0);

        for (Runner runner : runners) {
            runner.setParticipation(1);
            runner.setRunDate(LocalDate.now());

            userLevelRepository.updateLevel(runner.getUser());
            userLevelRepository.updateLevel(community.getUser());
        }

        runnerRepository.saveAll(runners);
    }
}