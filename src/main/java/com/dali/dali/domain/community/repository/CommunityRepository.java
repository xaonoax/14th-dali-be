package com.dali.dali.domain.community.repository;

import com.dali.dali.domain.community.entity.AMPM;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.entity.Gender;
import com.dali.dali.domain.community.entity.Time;
import com.dali.dali.domain.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository <Community, Long> {
    Page<Community> findByGender(Gender gender, Pageable pageable);
    Page<Community> findByTime(Time time, Pageable pageable);
    Page<Community> findByAmpm(AMPM ampm, Pageable pageable);
    Page<Community> findByGenderAndTimeAndAmpm(Gender gender, Time time, AMPM ampm, Pageable pageable);
    // 마이페이지에서 사용자가 작성한 글 가져오기 위해 추가(참여 확인을 하기 위함)
    List<Community> findByUser(User user);
}
