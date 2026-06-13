package com.delivery.ordermicroservice.mapper;

import com.delivery.ordermicroservice.dto.OrderRequestDto;
import com.delivery.ordermicroservice.entity.OrderEntity;

public class OrderMapper {

    public static OrderEntity mapOrderDtoToEntity(OrderRequestDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setPickupAddress(orderDto.getPickupAdress());
        orderEntity.setDeliveryAddress(orderDto.getDeliveryAdress());

        return orderEntity;
    }
}