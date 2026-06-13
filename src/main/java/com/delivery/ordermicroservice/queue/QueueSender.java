package com.delivery.ordermicroservice.queue;

import com.delivery.ordermicroservice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueSender {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.order}")
    private String orderExchange;

    @Value("${rabbitmq.routing-key.courier}")
    private String courierRoutingKey;

    @Value("${rabbitmq.routing-key.payment}")
    private String paymentRoutingKey;

    public void sendStatusMessageToCourierQueue(OrderEvent orderEvent) {
        try {
            amqpTemplate.convertAndSend(orderExchange, courierRoutingKey, orderEvent);
            log.info("Courier message sent. Exchange: {}, RoutingKey: {}, OrderId: {}",
                    orderExchange, courierRoutingKey, orderEvent.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send courier message. OrderId: {}", orderEvent.getOrderId(), e);
            throw new RuntimeException("Failed to send courier message", e);
        }
    }

    public void sendStatusMessageToPaymentQueue(OrderEvent orderEvent) {
        try {
            amqpTemplate.convertAndSend(orderExchange, paymentRoutingKey, orderEvent);
            log.info("Payment message sent. Exchange: {}, RoutingKey: {}, OrderId: {}",
                    orderExchange, paymentRoutingKey, orderEvent.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send payment message. OrderId: {}", orderEvent.getOrderId(), e);
            throw new RuntimeException("Failed to send payment message", e);
        }
    }
}