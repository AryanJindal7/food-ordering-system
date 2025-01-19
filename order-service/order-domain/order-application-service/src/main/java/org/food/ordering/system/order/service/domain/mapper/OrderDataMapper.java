package org.food.ordering.system.order.service.domain.mapper;

import org.food.ordering.system.domain.valueObject.CustomerId;
import org.food.ordering.system.domain.valueObject.Money;
import org.food.ordering.system.domain.valueObject.ProductId;
import org.food.ordering.system.domain.valueObject.RestaurantId;
import org.food.ordering.system.order.service.create.CreateOrderCommand;
import org.food.ordering.system.order.service.create.CreateOrderResponse;
import org.food.ordering.system.order.service.create.OrderAddress;
import org.food.ordering.system.order.service.domain.entity.Order;
import org.food.ordering.system.order.service.domain.entity.OrderItem;
import org.food.ordering.system.order.service.domain.entity.Product;
import org.food.ordering.system.order.service.domain.entity.Restaurant;
import org.food.ordering.system.order.service.domain.valueObject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){
        return Restaurant.builder().id(new RestaurantId(createOrderCommand.getRestaurantId())).products(createOrderCommand.getItems().stream().map(orderItem ->
                new Product(new ProductId(orderItem.getProduct().getId().getValue()))).collect(Collectors.toList()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand)
    {
        return Order.builder().customerId(new CustomerId(createOrderCommand.getCustomerID()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStringAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntities(createOrderCommand.getItems()))
                .build();

    }

    private List<OrderItem> orderItemsToOrderItemsEntities(List<OrderItem> items) {
        return items.stream().map(orderItem -> orderItem.builder()
                .product(new Product(new ProductId(orderItem.getProduct().getId().getValue())))
                .price(new Money(orderItem.getPrice().getAmount()))
                .quantity(orderItem.getQuantity())
                .subTotal(new Money(orderItem.getSubTotal().getAmount()))
                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStringAddress(OrderAddress address) {
        return new StreetAddress(UUID.randomUUID(),address.getStreet(),address.getPostalCode(),address.getPostalCode());
    }
}
