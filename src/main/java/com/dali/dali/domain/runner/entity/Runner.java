package com.dali.dali.domain.runner.entity;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("0")
    private int participation;

}