package com.dali.dali.domain.park;

import com.dali.dali.domain.park.dto.ParkResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {

}
