package com.dali.dali.domain.users.controller;

import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class OAuthController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    public JSONObject oauthLoginInfo(Principal principal) {
        JSONObject obj = new JSONObject();
        try {
            String email = principal.getName();
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User users = optionalUser.get();
                obj.put("success", true);
                obj.put("name", users.getName());
                obj.put("gender", users.getGender());
                obj.put("email", users.getEmail());
                obj.put("nickname", users.getNickname());
                obj.put("profile", users.getProfile());
            }
            return obj;
        } catch (NullPointerException e) {
            obj.put("success", false);
            return obj;
        }
    }
}
