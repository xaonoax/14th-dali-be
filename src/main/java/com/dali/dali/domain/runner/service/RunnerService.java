package com.dali.dali.domain.runner.service;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.runner.repository.RunRepository;
import com.dali.dali.domain.runner.repository.RunnerRepository;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import com.dali.dali.global.exception.DuplicateResourceException;
import com.dali.dali.global.exception.NotFoundException;
import com.dali.dali.global.exception.ParticipationAlreadyConfirmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RunnerService {
    private final RunRepository runRepository;
    private final RunnerRepository runnerRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public void addRunner(RunnerDto runnerDto) throws Exception {

        Community community = communityRepository.findById(runnerDto.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getCommunity_id() + " : 글이 존재하지 않습니다."));

        User user = userRepository.findById(runnerDto.getUser_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getUser_id() + " : 유저가 존재하지 않습니다."));

        if (community.getCurrentCount() >= community.getUserCount()) {
            throw new IllegalStateException("참여 마감되었습니다.");
        }

        // 이미 참여하기가 눌린 상태라면 에러 반환
        if (runnerRepository.findByUserAndCommunity(user, community).isPresent()) {
            throw new DuplicateResourceException("이미 참여하기 되어있는 상태입니다. : user: " + user.getUserId()
                    + ", community: " + community.getId());
        }

        Runner runner = Runner.builder()
                .user(user)
                .community(community)
                .build();

        runnerRepository.save(runner);
        runRepository.addRunner(community);
    }

    public void deleteRunner(RunnerDto runnerDto) throws Exception {
        Community community = communityRepository.findById(runnerDto.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getCommunity_id() + " : 글이 존재하지 않습니다."));

        User user = userRepository.findById(runnerDto.getUser_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getUser_id() + " : 유저가 존재하지 않습니다."));

        Runner runner = runnerRepository.findByUserAndCommunity(user, community)
                .orElseThrow(() -> new NotFoundException("참가하기 정보를 찾을 수 없습니다."));

        runnerRepository.delete(runner);
        runRepository.deleteRunner(community);
    }

    public void confirmRunner(Long community_id) throws Exception {

        if (runnerRepository.existsByCommunityIdAndParticipation(community_id, 1)) {
            throw new ParticipationAlreadyConfirmException(community_id + " : 참가확인이 완료된 러닝메이트입니다.");
        }

        List<Runner> runners = runnerRepository.findByCommunityIdAndParticipation(community_id, 0);

        for (Runner runner : runners) {
            runner.setParticipation(1);
        }
        runnerRepository.saveAll(runners);
    }
}