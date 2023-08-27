package com.dali.dali.domain.security.jwt;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccessTokenDTO {
    private String grantType;
    private String token;
    private Long tokenExpiresIn;
}