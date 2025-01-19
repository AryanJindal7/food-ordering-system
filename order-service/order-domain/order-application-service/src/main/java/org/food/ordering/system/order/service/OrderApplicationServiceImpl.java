package org.food.ordering.system.order.service;

import lombok.extern.slf4j.Slf4j;
import org.food.ordering.system.order.service.create.CreateOrderCommand;
import org.food.ordering.system.order.service.create.CreateOrderResponse;
import org.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import org.food.ordering.system.order.service.track.TrackOrderQuery;
import org.food.ordering.system.order.service.track.TrackOrderResponseClass;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;

    private final OrderTrackCommandHandler orderTrackCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponseClass trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrderResponseClass(trackOrderQuery);
    }
}
