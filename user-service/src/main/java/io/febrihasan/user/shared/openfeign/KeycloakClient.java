package io.febrihasan.user.shared.openfeign;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping(value = "/login")
    Map<String, String> login(@RequestBody LoginRequest request);

}
