package org.food.ordering.system.order.service.domain.ports.input.service;

import jakarta.validation.Valid;
import org.food.ordering.system.order.service.create.CreateOrderCommand;
import org.food.ordering.system.order.service.create.CreateOrderResponse;
import org.food.ordering.system.order.service.track.TrackOrderQuery;
import org.food.ordering.system.order.service.track.TrackOrderResponseClass;

public interface OrderApplicationService {

   CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

   TrackOrderResponseClass trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
