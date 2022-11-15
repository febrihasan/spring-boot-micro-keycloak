package io.febrihasan.keycloak.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class register request dto
 *
 * @author febrihasan
 */
@Data
public class RegisterRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
}
