package com.delivery.ordermicroservice.feignclient;

import com.delivery.ordermicroservice.dto.CourierResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "courier-service", url = "${courier.service.url}")
public interface CourierClient {

    @GetMapping("/api/couriers/available")
    List<CourierResponseDto> getAvailableCouriers();
}