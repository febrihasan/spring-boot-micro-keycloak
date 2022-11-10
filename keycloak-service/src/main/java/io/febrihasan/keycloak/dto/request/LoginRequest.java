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
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("password")
    private String password;
    @JsonProperty("signature")
    private String signature;
}
