package com.dali.dali.domain.community.controller;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.entity.AMPM;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.entity.Gender;
import com.dali.dali.domain.community.entity.Time;
import com.dali.dali.domain.community.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/communities")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public Community createPost(@RequestBody CommunityDto communityDto, Principal principal) {
        return communityService.createPost(communityDto, principal);
    }

    @GetMapping("/{id}")
    public Community getPost(@PathVariable Long id, Principal principal) {
        return communityService.getPost(id, principal);
    }

    @GetMapping
    public Page<Community> getPosts(Pageable pageable,
                                    @RequestParam(value = "ampm", required = false) AMPM ampm,
                                    @RequestParam(value = "time", required = false) Time time,
                                    @RequestParam(value = "gender", required = false) Gender gender,
                                    @RequestParam(value = "sido", required = false) String sido,
                                    @RequestParam(value = "sigungu", required = false) String sigungu,
                                    @RequestParam(value = "dong", required = false) String dong,
                                    Principal principal) {
        return communityService.getPosts(pageable, ampm, time, gender, sido, sigungu, dong, principal);
    }

    @PutMapping("/{id}")
    public Community updatePost(@PathVariable Long id, @RequestBody CommunityDto communityDto, Principal principal) {
        return communityService.updatePost(id, communityDto, principal);
    }

    @DeleteMapping("{id}")
    public Community deletePost(@PathVariable Long id, Principal principal) {
        return communityService.deletePost(id, principal);
    }
}
