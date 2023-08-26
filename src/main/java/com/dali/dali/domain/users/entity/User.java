package com.dali.dali.domain.users.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "naver_id", length = 70)
    private String naverId;

    @Column(length = 10)
    private String name;

    @Column(length = 1)
    private String gender;

    @Column(length = 30)
    private String email;

    @Column(length = 20)
    private String nickname;

    @Column(length = 255)
    private String profile;

    @Builder
    public User(String naverId, String name,
                String gender, String email,
                String nickname, String profile) {
        this.naverId = naverId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
    }
}
