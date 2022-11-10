package io.febrihasan.keycloak.controller;

import io.febrihasan.keycloak.configuration.provider.KeycloakProvider;
import io.febrihasan.keycloak.dto.request.LoginRequest;
import io.febrihasan.keycloak.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class KeycloakController {

    public final KeycloakProvider keycloakProvider;

    @GetMapping
    public String index() {
        return "Welcome to Keycloak Service";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<AccessTokenResponse> response = keycloakProvider
                .newKeycloakBuilderWithPasswordCredentials(
                        request.getPhoneNumber(), request.getPassword());

        LoginResponse result = new LoginResponse();
        result.setAccessToken(response.get().getToken());
        result.setTokenType(response.get().getTokenType());
        result.setRefreshToken(response.get().getRefreshToken());
        result.setPin(false);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userinfo")
    public String userInfo(Principal principal) {
        return principal.getName();
    }

}
