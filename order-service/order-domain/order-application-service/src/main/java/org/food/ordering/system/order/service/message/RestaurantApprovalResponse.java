package org.food.ordering.system.order.service.message;

import org.food.ordering.system.domain.valueObject.OrderApprovedStatus;

import java.time.Instant;
import java.util.List;

public class RestaurantApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String restaurantId;
    private Instant createdAt;
    private OrderApprovedStatus orderApprovedStatus;
    private List<String> failureMesasges;
}
