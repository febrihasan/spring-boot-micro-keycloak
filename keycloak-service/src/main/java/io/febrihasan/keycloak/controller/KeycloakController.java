package io.febrihasan.keycloak.controller;

import io.febrihasan.keycloak.configuration.provider.KeycloakProvider;
import io.febrihasan.keycloak.dto.request.LoginRequest;
import io.febrihasan.keycloak.dto.request.RegisterRequest;
import io.febrihasan.keycloak.dto.response.RegisterResponse;
import io.febrihasan.keycloak.openfeign.admin.KeycloakAdmin;
import io.febrihasan.keycloak.service.internal.KeycloakService;
import io.febrihasan.keycloak.utils.BaseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author febrihasan
 */
@Slf4j
@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
public class KeycloakController {

    private final KeycloakService keycloakService;

    @GetMapping
    public String index() {
        return "Welcome to Keycloak Service";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        log.info("Registration: {} {}", request.getUsername(), request.getEmail());
        UserRepresentation user = keycloakService.registration(request);
        if (BaseUtils.isNotNull(user)) {
            return ResponseEntity.ok(new RegisterResponse(user.getId(), user.getUsername()));
        }

        throw new RuntimeException();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request) {
        log.info("Login: {}", request.getUsername());
        return ResponseEntity.ok(keycloakService.login(request));
    }

    @DeleteMapping("/logout/{username}")
    public ResponseEntity<Boolean> logout(@PathVariable String username) {
        log.info("Logout key: {}", username);
        return ResponseEntity.ok(keycloakService.logout(username));
    }

    @GetMapping("/userinfo")
    public String userInfo(Principal principal) {
        return principal.getName();
    }

}
