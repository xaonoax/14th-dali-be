package com.dali.dali.domain.users.service;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.runner.repository.RunnerRepository;
import com.dali.dali.domain.users.dto.MyPageDTO;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import com.dali.dali.global.exception.NotFoundException;
import com.dali.dali.global.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final RunnerRepository runnerRepository;

    public MyPageDTO getMyPageInfo(Principal principal) {
        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String loginUser = principal.getName();

        User user = userRepository.findByEmail(loginUser).orElseThrow(
                () -> new NotFoundException("유저가 존재하지 않습니다."));

        List<Community> user_community = communityRepository.findByUser(user);
        List<Runner> user_runner = runnerRepository.findByUser(user);

        return MyPageDTO.builder()
                .user_community(user_community)
                .user_runner(user_runner)
                .user_id(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .level(user.getLevel())
                .build();
    }

}
