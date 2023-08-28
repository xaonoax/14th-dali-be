package com.dali.dali.domain.community.service;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.entity.AMPM;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.entity.Gender;
import com.dali.dali.domain.community.entity.Time;
import com.dali.dali.domain.community.repository.CommunityRepository;
import com.dali.dali.domain.park.entity.Park;
import com.dali.dali.domain.park.ParkRepository;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import com.dali.dali.global.exception.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final ParkRepository parkRepository;
    private final UserRepository userRepository;

    @Transactional
    public Community createPost(CommunityDto communityDto, Principal principal) {

        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        Park park = parkRepository.findById(communityDto.getPark_id())
                .orElseThrow(() -> new EntityNotFoundException(communityDto.getPark_id() + ": 공원을 찾을 수 없습니다."));

        User user = userRepository.findById(communityDto.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException(communityDto.getUser_id() + ": 유저를 찾을 수 없습니다."));

        Community community = Community.builder()
                .title(communityDto.getTitle())
                .content(communityDto.getContent())
                .gender(communityDto.getGender())
                .ampm(communityDto.getAmpm())
                .time(communityDto.getTime())
                .userCount(communityDto.getUserCount())
                .regDate(LocalDateTime.now())
                .park(park)
                .user(user)
                .build();

        return communityRepository.save(community);
    }

    public Community getPost(Long id, Principal principal) {

        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        return communityRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.")
        );
    }

    public Page<Community> getPosts(Pageable pageable, AMPM ampm,
                                    Time time, Gender gender, String park_name, Principal principal) {

        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (ampm == null && time == null && gender == null && park_name == null) {
            return communityRepository.findAll(pageable);
        } else if (gender != null) {
            return communityRepository.findByGender(gender, pageable);
        } else if (time != null) {
            return communityRepository.findByTime(time, pageable);
        } else if (ampm != null) {
            return communityRepository.findByAmpm(ampm, pageable);
        } else if (park_name != null) {
            return communityRepository.findByParkNameContaining(park_name, pageable);
        }
        return communityRepository.findByGenderAndTimeAndAmpmAndParkName(gender, time, ampm, park_name, pageable);
    }

    @Transactional
    public Community updatePost(Long id, CommunityDto communityDto, Principal principal) {
        Optional<Community> existPost = communityRepository.findById(id);
        if (existPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 게시글이 존재하지 않습니다.");
        }

        Park park = parkRepository.findById(communityDto.getPark_id())
                .orElseThrow(() -> new EntityNotFoundException(communityDto.getPark_id() + ": 공원을 찾을 수 없습니다."));

        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String email = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User loginUser = optionalUser.get();
        User communityUser = existPost.get().getUser();

        if (!loginUser.getEmail().equals(communityUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 게시글 수정이 가능합니다.");
        }

        Community community = Community.builder()
                .title(communityDto.getTitle())
                .content(communityDto.getContent())
                .gender(communityDto.getGender())
                .ampm(communityDto.getAmpm())
                .time(communityDto.getTime())
                .userCount(communityDto.getUserCount())
                .regDate(existPost.get().getRegDate())
                .updateDate(LocalDateTime.now())
                .park(park)
                .build();

        return communityRepository.save(community);
    }

    @Transactional
    public Community deletePost(Long id, Principal principal) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 게시글이 존재하지 않습니다.")
        );

        if (principal == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String email = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User loginUser = optionalUser.get();
        User communityUser = community.getUser();

        if (!loginUser.getEmail().equals(communityUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 게시글 삭제가 가능합니다.");
        }

        communityRepository.deleteById(id);
        return community;
    }
}
