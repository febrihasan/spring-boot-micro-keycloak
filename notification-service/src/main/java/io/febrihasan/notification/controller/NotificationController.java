package io.febrihasan.notification.controller;

import io.febrihasan.notification.service.internal.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author febrihasan
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final KafkaProducerService kafkaProducerService;

    @GetMapping
    public String index() {
        return "Welcome to Notification Service";
    }

    @PostMapping("/publish")
    private void sendMessageToKafkaTopic(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
    }

}
