package com.dali.dali.domain.park;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_id")
    private Long id;

    @Column(name = "park_manage_no")
    private String manageNo;

    @Column(name = "park_name")
    private String name;

    @Column(name = "park_address")
    private String address;

    @Column(name = "park_longitude")
    private double longitude;

    @Column(name = "park_latitude")
    private double latitude;


}
