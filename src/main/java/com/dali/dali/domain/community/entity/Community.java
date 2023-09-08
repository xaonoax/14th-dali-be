package com.dali.dali.domain.community.entity;

import com.dali.dali.domain.city.entity.City;
import com.dali.dali.domain.community.dto.CommunityDto;
import com.dali.dali.domain.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AMPM ampm;

    @Column(nullable = false)
    @Convert(converter = TimeConverter.class)
    private Time time;

    @Column(nullable = false)
    private int userCount;

    @ColumnDefault("0")
    private int currentCount;

    @ManyToOne
    @JoinColumn(name = "city_code")
    private City city;

    // 게시글 수정 로직 추가
    public Community updatePost(CommunityDto communityDto, User user, City city) {
        this.title = communityDto.getTitle();
        this.content = communityDto.getContent();
        this.gender = communityDto.getGender();
        this.ampm = communityDto.getAmpm();
        this.time = communityDto.getTime();
        this.userCount = communityDto.getUserCount();
        this.updateDate = LocalDateTime.now();
        this.user = user;
        this.city = city;
        return this;
    }
}
