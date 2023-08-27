package com.dali.dali.domain.runner.service;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.runner.repository.RunRepository;
import com.dali.dali.domain.runner.repository.RunnerRepository;
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

    public void addRunner(RunnerDto runnerDto) throws Exception {

        Community community = communityRepository.findById(runnerDto.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getCommunity_id() + " : 글이 존재하지 않습니다."));

        if (community.getCurrentCount() >= community.getUserCount()) {
            throw new IllegalStateException("참여 마감되었습니다.");
        }

        Runner runner = Runner.builder()
                .user_id(runnerDto.getUser_id())
                .community(community)
                .build();

        runnerRepository.save(runner);
        runRepository.addRunner(community);
    }

    public void deleteRunner(RunnerDto runnerDto) {
        Community community = communityRepository.findById(runnerDto.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(runnerDto.getCommunity_id() + " : 글이 존재하지 않습니다."));

        // 유저 추가 예정

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