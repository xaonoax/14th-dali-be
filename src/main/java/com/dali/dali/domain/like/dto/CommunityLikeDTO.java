package com.dali.dali.domain.like.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityLikeDTO {
    private Long user_id;
    private Long community_id;
}
