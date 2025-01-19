package org.food.ordering.system.order.service.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.food.ordering.system.domain.valueObject.OrderStatus;
import org.food.ordering.system.order.service.domain.entity.Order;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class TrackOrderResponseClass {
    private final UUID orderTrackingId;
    private final OrderStatus orderStatus;
    private final List<String> failureMessages;
}
