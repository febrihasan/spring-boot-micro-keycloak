package io.febrihasan.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @GetMapping
    public String index() {
        return "Welcome to Report Service";
    }

}
