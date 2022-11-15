package io.febrihasan.gateway.v2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/gateway-v2")
public class GatewayV2Controller {

    @GetMapping
    public String index() {
        return "Welcome to API Gateway V2 Service";
    }

}
