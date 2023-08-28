package com.dali.dali.domain.park;

import com.dali.dali.domain.park.dto.ParkResponseDto;
import com.dali.dali.domain.park.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

    List<Park> findByNameOrAddressContaining(String word1, String word2);
}
