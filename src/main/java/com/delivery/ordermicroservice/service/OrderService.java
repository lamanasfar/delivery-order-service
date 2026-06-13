package com.delivery.ordermicroservice.service;

import com.delivery.ordermicroservice.dto.CourierResponseDto;
import com.delivery.ordermicroservice.dto.OrderRequestDto;
import com.delivery.ordermicroservice.entity.OrderEntity;
import com.delivery.ordermicroservice.enums.OrderStatus;
import com.delivery.ordermicroservice.feignclient.CourierClient;
import com.delivery.ordermicroservice.mapper.OrderMapper;
import com.delivery.ordermicroservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CourierClient courierClient;



    public void createOrder(OrderRequestDto orderRequestDto) {

        if (orderRequestDto == null) {
            throw new IllegalArgumentException("Order request cannot be null");
        }

        OrderEntity order = OrderMapper.mapOrderDtoToEntity(orderRequestDto);
        order.setOrderStatus(OrderStatus.CREATED);

        orderRepository.save(order);
    }

    public void updateOrderAssignedOrRejected(Long orderId) {

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<CourierResponseDto> couriers =
                courierClient.getAvailableCouriers();

        if (couriers.isEmpty()) {

            order.setOrderStatus(OrderStatus.REJECTED);
            orderRepository.save(order);

        } else {

            CourierResponseDto courier = couriers.getFirst();

            order.setCourierId(courier.getCourierId());
            order.setOrderStatus(OrderStatus.ASSIGNED);

            orderRepository.save(order);
        }
    }
}