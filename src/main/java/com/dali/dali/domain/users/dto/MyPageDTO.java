package com.dali.dali.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPageDTO {
    private Long user_id;
    private String email;
    private String nickname;
    private String profile;
    private int level;

}
