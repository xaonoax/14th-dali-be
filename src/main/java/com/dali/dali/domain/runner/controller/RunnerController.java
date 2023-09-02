package com.dali.dali.domain.runner.controller;

import com.dali.dali.domain.runner.dto.RunnerDto;
import com.dali.dali.domain.runner.service.RunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;

    @PostMapping
    public void addRunner(@RequestBody RunnerDto runnerDto, Principal principal) throws Exception {
        runnerService.addRunner(runnerDto, principal);
    }

    @DeleteMapping
    public void deleteRunner(@RequestBody RunnerDto runnerDto, Principal principal) throws Exception {
        runnerService.deleteRunner(runnerDto, principal);
    }
}
