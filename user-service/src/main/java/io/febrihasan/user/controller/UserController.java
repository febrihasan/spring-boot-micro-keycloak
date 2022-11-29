package io.febrihasan.user.controller;

import io.febrihasan.user.dto.request.LoginRequest;
import io.febrihasan.user.dto.request.RegistrationRequest;
import io.febrihasan.user.dto.response.LoginResponse;
import io.febrihasan.user.dto.response.RegistrationResponse;
import io.febrihasan.user.service.internal.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String index() {
        return "Welcome to User Service";
    }

    @GetMapping("/name")
    public String getName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/token")
    public String getTokenDetails(@RequestHeader HttpHeaders headers) {
        return headers.toString();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(new RegistrationResponse(userService.registration(request)));
    }

    @DeleteMapping("/logout/{username}")
    public ResponseEntity<Void> logout(@PathVariable String username) {
        userService.logout(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
