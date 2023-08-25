package com.dali.dali.domain.runner.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.runner.entity.Runner;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRepository {
    void addRunner(Community community);
    void deleteRunner(Community community);

    void confirmRunner(Runner runner);
}
