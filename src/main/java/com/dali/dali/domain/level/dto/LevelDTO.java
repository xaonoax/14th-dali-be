package com.dali.dali.domain.level.dto;

import com.dali.dali.domain.runner.entity.Runner;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LevelDTO {
    private Runner runner;
    private int levelName;
}
