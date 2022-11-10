package io.febrihasan.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @GetMapping
    public String index() {
        return "Welcome to Config Service";
    }
}
