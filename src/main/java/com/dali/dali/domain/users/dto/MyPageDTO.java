package com.dali.dali.domain.users.dto;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPageDTO {
    private List<Community> user_community;
    private List<Runner> user_runner;
    private Long user_id;
    private String email;
    private String nickname;
    private String profile;
    private int level;

}
