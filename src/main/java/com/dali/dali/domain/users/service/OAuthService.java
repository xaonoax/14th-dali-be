package com.dali.dali.domain.users.service;

import com.dali.dali.domain.users.dto.UserDTO;
import com.dali.dali.domain.users.entity.User;
import com.dali.dali.domain.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JSONParser jsonParser;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    public String getAccessToken(String code, String state) {
        return extractAccessToken(requestAccessToken(requestAuthCode(code, state)).getBody());
    }

    public UserDTO getUserProfile(String accessToken) {
        String profileJson = requestUserProfile(requestCreateProfile(accessToken)).getBody();
        UserDTO userDTO = extractUserProfile(profileJson);
        saveUserFromNaverProfile(userDTO);  // DB에 사용자 정보 저장
        return userDTO;
    }

    @Transactional
    public User saveUserFromNaverProfile(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findByNaverId(userDTO.getNaverId());
        if (existUser.isPresent()) {
            return existUser.get();
        }

        User user = User.builder()
                .naverId(userDTO.getNaverId())
                .name(userDTO.getName())
                .gender(userDTO.getGender())
                .email(userDTO.getEmail())
                .nickname(userDTO.getNickname())
                .profile(userDTO.getProfile())
                .build();

        return userRepository.save(user);
    }

    public UserDTO extractUserProfile(String extractUserProfile) {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(extractUserProfile);
            JSONObject response = (JSONObject) jsonObject.get("response");

            UserDTO userDTO = new UserDTO();
            userDTO.setNaverId((String) response.get("id"));
            userDTO.setName((String) response.get("name"));
            userDTO.setGender((String) response.get("gender"));
            userDTO.setEmail((String) response.get("email"));
            userDTO.setNickname((String) response.get("nickname"));
            userDTO.setProfile((String) response.get("profile_image"));

            return userDTO;
        }
        catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    private HttpEntity<MultiValueMap<String, String>> requestAuthCode(String code, String state) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("state", state);

        return new HttpEntity<>(params, headers);
    }

    private ResponseEntity<String> requestAccessToken(HttpEntity request) {
        return restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                request,
                String.class
        );
    }

    private ResponseEntity<String> requestUserProfile(HttpEntity request) {
        return restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                request,
                String.class
        );
    }

    private HttpEntity<MultiValueMap<String, String>> requestCreateProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return new HttpEntity<>(headers);
    }

    private String extractAccessToken(String response) {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            return (String) jsonObject.get("access_token");
        }
        catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }
}
