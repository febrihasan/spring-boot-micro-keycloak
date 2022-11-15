package io.febrihasan.keycloak.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class login request dto
 *
 * @author febrihasan
 */
@Data
public class LoginRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
