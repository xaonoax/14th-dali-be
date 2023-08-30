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
import org.springframework.web.bind.annotation.*;

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
                                    Principal principal) {
        return communityService.getPosts(pageable, ampm, time, gender, principal);
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
