package com.dali.dali.domain.park.dto;

import com.dali.dali.domain.park.Park;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ParkResponseDto {

    private Long id;

    private String manageNo;

    private String name;

    private String address;

    private double longitude;

    private double latitude;

    public ParkResponseDto(Park park) {
        this.id = park.getId();
        this.manageNo = park.getManageNo();
        this.name = park.getName();
        this.address = park.getAddress();
        this.longitude = park.getLongitude();
        this.latitude = park.getLatitude();
    }
}