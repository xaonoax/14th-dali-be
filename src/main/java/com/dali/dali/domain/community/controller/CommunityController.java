package com.dali.dali.domain.community.controller;

import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/communities")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public Community createPost(@RequestBody CommunityDto communityDto) {
        return communityService.createPost(communityDto);
    }
}
