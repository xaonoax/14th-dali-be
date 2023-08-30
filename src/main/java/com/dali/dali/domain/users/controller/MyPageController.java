package com.dali.dali.domain.users.controller;

import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.service.RunnerService;
import com.dali.dali.domain.users.dto.MyPageDTO;
import com.dali.dali.domain.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/mypage")
@AllArgsConstructor
public class MyPageController {
    private final UserService userService;
    private final RunnerService runnerService;
    @GetMapping
    public MyPageDTO getMyPageInfo(Principal principal) {
        return userService.getMyPageInfo(principal);
    }

    // 러닝메이트 참여 확인(API 변경)
    @PostMapping("/runner/{community_id}")
    public void confirmRunner(@PathVariable Long community_id, @RequestBody RunnerDto runnerDto) throws Exception {
        runnerService.confirmRunner(runnerDto, community_id);
    }
}
