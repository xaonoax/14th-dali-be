package com.dali.dali.domain.like.service;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.like.dto.CommunityLikeDTO;
import com.dali.dali.domain.like.entity.CommunityLike;
import com.dali.dali.domain.like.repository.CommunityLikeRepository;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import com.dali.dali.global.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityLikeService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityLikeRepository communityLikeRepository;


    public void incrementLikeUser(CommunityLikeDTO communityLikeDTO) throws Exception {
        User user = userRepository.findById(communityLikeDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException(communityLikeDTO.getUser_id() + " : 유저가 존재하지 않습니다."));

        Community community = communityRepository.findById(communityLikeDTO.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(communityLikeDTO.getCommunity_id() + " : 글이 존재하지 않습니다."));

        if(community.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("자신의 게시물에는 좋아요를 할 수 없습니다.");
        }

        CommunityLike communityLike = CommunityLike.builder()
                .user(user)
                .community(community)
                .build();

        communityLikeRepository.save(communityLike);
    }

    public void decrementLikeUser(CommunityLikeDTO communityLikeDTO) throws Exception {
        User user = userRepository.findById(communityLikeDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException(communityLikeDTO.getCommunity_id() + " : 유저가 존재하지 않습니다."));

        Community community = communityRepository.findById(communityLikeDTO.getCommunity_id())
                .orElseThrow(() -> new NotFoundException(communityLikeDTO.getCommunity_id() + " : 글이 존재하지 않습니다."));

        CommunityLike communityLike = communityLikeRepository.findByUserAndCommunity(user, community)
                .orElseThrow(() -> new NotFoundException("사용자의 정보를 찾을 수 없습니다."));


        communityLikeRepository.delete(communityLike);
    }
}
