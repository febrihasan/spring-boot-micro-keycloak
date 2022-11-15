package io.febrihasan.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String index() {
        return "Welcome to Admin Service";
    }

}
