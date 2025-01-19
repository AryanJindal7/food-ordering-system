package org.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.food.ordering.system.domain.valueObject.OrderStatus;

import java.util.UUID;

@Getter @AllArgsConstructor @Builder
public class CreateOrderResponse {

    private final UUID orderTrackingId;
    private final OrderStatus orderStatus;
    private final String message;
}
