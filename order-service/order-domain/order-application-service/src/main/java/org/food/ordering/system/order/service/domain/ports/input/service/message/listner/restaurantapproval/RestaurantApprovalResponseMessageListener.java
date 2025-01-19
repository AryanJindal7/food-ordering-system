package org.food.ordering.system.order.service.domain.ports.input.service.message.listner.restaurantapproval;

import org.food.ordering.system.order.service.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
