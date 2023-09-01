package com.dali.dali.domain.community.repository;

import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository <Community, Long>, JpaSpecificationExecutor<Community> {
    List<Community> findByUser(User user);
}
