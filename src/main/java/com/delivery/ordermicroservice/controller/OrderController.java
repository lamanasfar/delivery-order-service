package com.delivery.ordermicroservice.controller;

import com.delivery.ordermicroservice.dto.OrderRequestDto;
import com.delivery.ordermicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.createOrder(orderRequestDto);
    }

    @PutMapping("/{orderId}")
    public void updateOrderAssignedOrRejected(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequestDto) {
        orderService.updateOrderAssignedOrRejected(orderId);
    }
}