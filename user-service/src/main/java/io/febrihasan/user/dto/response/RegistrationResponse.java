package io.febrihasan.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author febrihasan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    @JsonProperty("result")
    private boolean result;
}
