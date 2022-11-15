package io.febrihasan.mail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @GetMapping
    public String index() {
        return "Welcome to Mail Service";
    }

}
