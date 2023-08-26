package com.dali.dali.domain.park;

import com.dali.dali.domain.park.dto.ParkResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ParkService {

    private final ParkRepository parkRepository;
    @Transactional(readOnly = true)
    public List<ParkResponseDto> getParks() {
        return parkRepository.findAll().stream()
                .map(ParkResponseDto::new)
                .collect(Collectors.toList());
    }


}
