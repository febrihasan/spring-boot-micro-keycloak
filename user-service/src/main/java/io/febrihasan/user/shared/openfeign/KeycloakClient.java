package io.febrihasan.user.shared.openfeign;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author febrihasan
 */
@FeignClient(
        value = "${restclient.keycloak.name}",
        url = "${restclient.keycloak.url}",
        path = "${restclient.keycloak.path}",
        fallbackFactory = KeycloakClientFallbackFactory.class
)
public interface KeycloakClient {

    @PostMapping("/register")
    Map<String, String> registration(@RequestBody RegistrationRequest request);

    @PostMapping("/login")
    Map<String, String> login(@RequestBody LoginRequest request);

    @DeleteMapping("/logout/{username}")
    Map<String, String> logout(@RequestHeader("Authorization") String authorization,
                               @PathVariable String username);

}
