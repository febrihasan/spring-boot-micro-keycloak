package io.febrihasan.keycloak.controller;

import io.febrihasan.keycloak.configuration.provider.KeycloakProvider;
import io.febrihasan.keycloak.dto.request.LoginRequest;
import io.febrihasan.keycloak.dto.request.RegisterRequest;
import io.febrihasan.keycloak.dto.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * @author febrihasan
 */
@Slf4j
@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
public class KeycloakController {

    public final KeycloakProvider keycloakProvider;

    @GetMapping
    public String index() {
        return "Welcome to Keycloak Service";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        log.info("Registration: {} {}", request.getUsername(), request.getEmail());
        Optional<UserRepresentation> response = Optional.ofNullable(keycloakProvider
                .registerNewUser(
                        request.getUsername(), request.getPassword(), request.getEmail()));
        if (response.isPresent()) {
            return ResponseEntity.ok(new RegisterResponse(response.get().getId(), response.get().getUsername()));
        }

        throw new RuntimeException();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request) {
        log.info("Login: {}", request.getUsername());
        Optional<AccessTokenResponse> response = keycloakProvider
                .newKeycloakBuilderWithPasswordCredentials(
                        request.getUsername(), request.getPassword());
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }

        throw new RuntimeException();
    }

    @GetMapping("/userinfo")
    public String userInfo(Principal principal) {
        return principal.getName();
    }

}
