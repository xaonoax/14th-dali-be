package com.dali.dali.domain.users.controller;

import com.dali.dali.domain.users.dto.UserDTO;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.service.OAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/login")
    public UserDTO requestNaver(@RequestParam String code, @RequestParam String state) {
        String accessToken = oAuthService.getAccessToken(code, state);
        return oAuthService.getUserProfile(accessToken); // DTO 반환
    }

    @PostMapping("/login")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return oAuthService.saveUserFromNaverProfile(userDTO);
    }

}
