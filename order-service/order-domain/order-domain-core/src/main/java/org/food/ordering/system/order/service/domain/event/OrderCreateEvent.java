package org.food.ordering.system.order.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.food.ordering.system.domain.event.DomainEvent;
import org.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;


public class OrderCreateEvent  extends OrderEvent {
    public OrderCreateEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
