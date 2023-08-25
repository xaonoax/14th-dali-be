package com.dali.dali.domain.community.dto;

import com.dali.dali.domain.community.entity.AMPM;
import com.dali.dali.domain.community.entity.Gender;
import com.dali.dali.domain.community.entity.Time;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {
    private Long id;
    private Long user_id;
    private Long park_id;
    private String title;
    private String content;
    private Gender gender;
    private AMPM ampm;
    private Time time;
    private int userCount;
    private int currentCount;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
