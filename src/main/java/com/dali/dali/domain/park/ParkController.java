package com.dali.dali.domain.park;

import com.dali.dali.domain.park.dto.ParkResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public List<ParkResponseDto> searchParks(@RequestParam String keyword) {
        return parkService.searchParksBySearchWord(keyword);
}
    }
