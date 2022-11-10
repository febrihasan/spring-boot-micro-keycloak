package io.febrihasan.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/api")
public class GatewayController {

    @GetMapping
    public String index() {
        return "Welcome to API Gateway Service";
    }

}
