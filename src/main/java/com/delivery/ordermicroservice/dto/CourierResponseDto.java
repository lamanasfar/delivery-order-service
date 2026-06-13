package com.delivery.ordermicroservice.dto;

import com.delivery.ordermicroservice.enums.CourierStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourierResponseDto {

    private Long courierId;
    private String courierName;
    private CourierStatus courierStatus;
}