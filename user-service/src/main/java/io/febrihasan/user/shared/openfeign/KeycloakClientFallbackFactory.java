package io.febrihasan.user.shared.openfeign;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author febrihasan
 */
@Slf4j
@Component
public class KeycloakClientFallbackFactory implements FallbackFactory<KeycloakClient> {

    @Override
    public KeycloakClient create(Throwable cause) {
        log.error("An exception occurred when calling the keycloak client", cause);
        return new KeycloakClient() {

            @Override
            public Map<String, String> registration(RegistrationRequest request) {
                log.error("Registration exception");
                return null;
            }

            @Override
            public Map<String, String> login(LoginRequest request) {
                log.error("Login exception");
                return new HashMap<>();
            }

            @Override
            public Map<String, String> logout(String authorization, String username) {
                return null;
            }
        };
    }
}
