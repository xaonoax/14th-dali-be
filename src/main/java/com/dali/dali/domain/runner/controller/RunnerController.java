package com.dali.dali.domain.runner.controller;

import com.dali.dali.domain.community.service.CommunityService;
import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.service.RunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;
    private final CommunityService communityService;

    @PostMapping
    public void addRunner(@RequestBody RunnerDto runnerDto) throws Exception {
        runnerService.addRunner(runnerDto);
    }

    @PostMapping("/{community_id}")
    public void confirmRunner(@PathVariable Long community_id) throws Exception {
        runnerService.confirmRunner(community_id);
    }
}
