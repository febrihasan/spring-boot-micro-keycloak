package io.febrihasan.user.transform;

import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

/**
 * @author febrihasan
 */
@Mapper(componentModel = "spring")
public interface UserTransform {

    RegistrationResponse convertRegistrationToDto(Map<String, String> response);

    @Mapping(source = "access_token", target = "accessToken")
    @Mapping(source = "refresh_token", target = "refreshToken")
    @Mapping(source = "token_type", target = "tokenType")
    @Mapping(target = "pin", ignore = true)
    @Mapping(target = "name", ignore = true)
    LoginResponse convertLoginToDto(Map<String, String> response);

}
