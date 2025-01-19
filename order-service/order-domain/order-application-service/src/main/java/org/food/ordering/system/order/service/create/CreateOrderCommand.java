package org.food.ordering.system.order.service.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.food.ordering.system.order.service.domain.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    private final UUID customerID;
    private final UUID restaurantId;
    private final BigDecimal price;
    private final List<OrderItem> items;
    private final OrderAddress address;

}
