package com.dali.dali.domain.community.entity;

import com.dali.dali.domain.city.entity.City;
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

}
