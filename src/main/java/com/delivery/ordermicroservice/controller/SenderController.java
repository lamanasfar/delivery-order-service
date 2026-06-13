package com.delivery.ordermicroservice.controller;

import com.delivery.ordermicroservice.model.OrderEvent;
import com.delivery.ordermicroservice.queue.QueueSender;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SenderController {

    private final QueueSender queueSender;

    @PostMapping("/sender")
    public String sendOrderToCourierQueue(@RequestBody OrderEvent orderEvent) {

        queueSender.sendStatusMessageToCourierQueue(orderEvent);

        return "Order status message has been sent";
    }
}