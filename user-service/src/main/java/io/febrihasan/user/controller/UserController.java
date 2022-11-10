package io.febrihasan.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/user")
public class UserController {

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

}
