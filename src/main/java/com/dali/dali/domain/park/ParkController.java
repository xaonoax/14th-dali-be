package com.dali.dali.domain.park;

import com.dali.dali.domain.park.dto.ParkResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parks")
@AllArgsConstructor
public class ParkController {

    private final ParkService parkService;

    @GetMapping
    public List<ParkResponseDto> getParks() {
        return parkService.getParks();
    }

    @GetMapping("/{keyword}")
    public List<ParkResponseDto> searchParks(@PathVariable String keyword) {
        return new ArrayList<>();
}
    }
