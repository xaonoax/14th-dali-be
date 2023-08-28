package com.dali.dali.domain.users.mapper;

import com.dali.dali.domain.users.entity.Role;
import com.dali.dali.domain.users.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String gender, String email, String nickname, String profile) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
    }

    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String gender;
    private final String email;
    private final String nickname;
    private final String profile;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofNaver(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .gender((String) response.get("gender"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .profile((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .gender(gender)
                .email(email)
                .nickname(nickname)
                .profile(profile)
                .role(Role.GUEST)
                .build();
    }
}
