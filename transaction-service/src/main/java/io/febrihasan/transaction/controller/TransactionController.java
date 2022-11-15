package io.febrihasan.transaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping
    public String index() {
        return "Welcome to Transaction Service";
    }

    @GetMapping("/name")
    public String getName(Principal principal) {
        return principal.getName();
    }

}
