package com.win.controller;

import com.win.service.OAuth2TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final OAuth2TokenService tokenService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        if(principal == null ) {
            return "cc";
        }
        return "Hello, " + principal.getAttribute("name") + "!";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if(principal == null ) {
            return Map.ofEntries();
        }
        return principal.getAttributes();
    }

    @GetMapping("/token")
    public String getToken(OAuth2AuthenticationToken authentication) {
        return tokenService.getAccessToken(authentication);
    }
}
