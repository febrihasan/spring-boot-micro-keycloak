package io.febrihasan.user.service.internal.impl;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;
import io.febrihasan.user.service.internal.UserService;
import io.febrihasan.user.shared.openfeign.KeycloakClient;
import io.febrihasan.user.transform.UserTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author febrihasan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakClient keycloakClient;

    private final UserTransform userTransform;

    @Override
    public boolean registration(RegistrationRequest request) {
        Optional<Map<String, String>> response = Optional.ofNullable(keycloakClient.registration(request));
        log.info("Response in user: {}", response);
        return response.isPresent();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Login Response: {}", keycloakClient.login(request));
        return userTransform.convertLoginToDto(keycloakClient.login(request));
    }
}
