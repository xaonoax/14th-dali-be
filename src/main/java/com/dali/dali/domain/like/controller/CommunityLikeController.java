package com.dali.dali.domain.like.controller;

import com.dali.dali.domain.like.dto.CommunityLikeDTO;
import com.dali.dali.domain.like.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class CommunityLikeController {
    private final CommunityLikeService communityLikeService;

    @PostMapping("")
    public void incrementLikeUser (@RequestBody CommunityLikeDTO communityLikeDTO) throws Exception {
        communityLikeService.incrementLikeUser(communityLikeDTO);
    }

    @DeleteMapping("")
    public void decrementLikeUser (@RequestBody CommunityLikeDTO communityLikeDTO) throws Exception {
        communityLikeService.decrementLikeUser(communityLikeDTO);
    }
}
