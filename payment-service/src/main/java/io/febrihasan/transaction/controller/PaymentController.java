package io.febrihasan.transaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping
    public String index() {
        return "Welcome to Payment Service";
    }

    @GetMapping("/name")
    public String getName(Principal principal) {
        return principal.getName();
    }

}
