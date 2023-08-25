package com.dali.dali.domain.runner.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RunnerDto {
    private Long user_id;
    private Long community_id;
    private int participation;
}
