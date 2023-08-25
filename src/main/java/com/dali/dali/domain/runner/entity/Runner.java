package com.dali.dali.domain.runner.entity;

import com.dali.dali.domain.community.entity.Community;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runner_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    // 유저 추후 추가
    private Long user_id;

    private int participation;

}