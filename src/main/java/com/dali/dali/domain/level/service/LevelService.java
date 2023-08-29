package com.dali.dali.domain.level.service;

import com.dali.dali.domain.level.entity.Level;
import com.dali.dali.domain.level.repository.LevelRepository;
import com.dali.dali.domain.level.repository.LevelUpdateRepository;
import com.dali.dali.domain.runner.entity.Runner;
import com.dali.dali.domain.runner.repository.RunnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LevelService {
    private final LevelRepository levelRepository;
    private final RunnerRepository runnerRepository;
    private final LevelUpdateRepository levelUpdateRepository;

    public void updateLevel(Long runner_id) {

        Optional<Runner> optionalRunner = runnerRepository.findByIdAndParticipation(runner_id, 1);

        if (optionalRunner.isPresent()) {
            Runner runner = optionalRunner.get();

            Level level = Level.builder()
                    .runner(runner)
                    .build();

            levelRepository.save(level);
            levelUpdateRepository.updateLevel(level);
        }
    }
}
