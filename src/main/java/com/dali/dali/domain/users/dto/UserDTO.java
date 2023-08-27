package com.dali.dali.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {
    private String naverId;
    private String name;
    private String gender;
    private String email;
    private String nickname;
    private String profile;
}
