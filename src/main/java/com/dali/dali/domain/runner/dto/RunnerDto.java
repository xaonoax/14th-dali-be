package com.dali.dali.domain.runner.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RunnerDto {
    private Long user_id;
    private Long community_id;
    private int participation;
    private LocalDate runDate;
}
