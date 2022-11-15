package io.febrihasan.user.service.internal;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;

/**
 * @author febrihasan
 */
public interface UserService {

    boolean registration(RegistrationRequest request);

    LoginResponse login(LoginRequest request);

}
