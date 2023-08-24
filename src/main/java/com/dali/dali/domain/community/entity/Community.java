package com.dali.dali.domain.community.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    private Long userId;
    private Long parkId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AMPM ampm;

    @Enumerated(EnumType.STRING)
    private Time time;

    private int userCount;
    private int currentCount;
}
