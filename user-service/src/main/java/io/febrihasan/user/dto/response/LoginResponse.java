package io.febrihasan.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * Class login response dto
 *
 * @author febrihasan
 */
@Data
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("is_pin")
    private boolean isPin;
    @JsonProperty("name")
    private String name;

    /*
     * Change value to first letter with capital
     */
    public String getTokenType() {
        return StringUtils.capitalize(tokenType);
    }
}
