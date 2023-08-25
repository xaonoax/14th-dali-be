package com.dali.dali.domain.community.service;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Community createPost(CommunityDto communityDto) {

        // 로그인 검증 추후 추가

        Community community = Community.builder()
                .title(communityDto.getTitle())
                .content(communityDto.getContent())
                .gender(communityDto.getGender())
                .ampm(communityDto.getAmpm())
                .time(communityDto.getTime())
                .userCount(communityDto.getUserCount())
                .regDate(LocalDateTime.now())
                .build();

        return communityRepository.save(community);
    }
}
