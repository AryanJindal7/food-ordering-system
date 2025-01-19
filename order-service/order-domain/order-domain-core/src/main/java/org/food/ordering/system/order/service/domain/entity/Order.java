package org.food.ordering.system.order.service.domain.entity;

import lombok.Getter;
import org.food.ordering.system.domain.entity.AggregateRoot;
import org.food.ordering.system.domain.valueObject.*;
import org.food.ordering.system.order.service.domain.exception.OrderDomainException;
import org.food.ordering.system.order.service.domain.valueObject.OrderItemId;
import org.food.ordering.system.order.service.domain.valueObject.StreetAddress;
import org.food.ordering.system.order.service.domain.valueObject.TrackingId;

import java.util.List;
import java.util.UUID;

@Getter
public class Order extends AggregateRoot<OrderId> {
private final CustomerId customerId;
private final RestaurantId restaurantId;
private final StreetAddress deliveryAddress;
private final Money price;
private final List<OrderItem> items;

private TrackingId trackingId;
private  OrderStatus orderStatus;
private List<String> failureMessage;

public void initializeOrder(){
    setId(new OrderId(UUID.randomUUID()));
    trackingId=new TrackingId(UUID.randomUUID());
    orderStatus= OrderStatus.PENDING;

    initializeOrderItems();
}

public void validateOrder(){
    validateInitialOrder();
    validateTotalPrice();
    validateItemPrice();
}
public void pay(){
    if(orderStatus != OrderStatus.PENDING)
    {
        throw new OrderDomainException("Order is not in correct state for pay operation !");
    }
    orderStatus=OrderStatus.PAID;
}

public void approve(){
    if(orderStatus!=OrderStatus.PAID){
        throw new OrderDomainException("Order is not in correct state for approve operation !");
    }
    orderStatus=OrderStatus.APPROVED;
}
public void initCancel(List<String> failureMessage){
    if(orderStatus!=OrderStatus.PAID)
    {
        throw new OrderDomainException("Order is not in correct state for initCancel operation !");

    }
    orderStatus=OrderStatus.CANCELLING;
    updateFailureMessages(failureMessage);
}

    private void updateFailureMessages(List<String> failureMessage) {
    if(this.failureMessage !=null && failureMessage!=null)
    {
        this.failureMessage.addAll(failureMessage.stream().filter(message-> !message.isEmpty()).toList());
    }
    if(this.failureMessage==null)
    {
        this.failureMessage=failureMessage;
    }
    }

  public   void cancel(List<String> failureMessage){
    if (orderStatus==OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)
    {
        throw new OrderDomainException("Order is not in correct state for cancel operation !");
    }
    orderStatus=OrderStatus.CANCALLED;
}

    private void validateItemPrice() {
   Money orderItemsTotal= items.stream().map(orderItem -> {
        validateItemsPrice(orderItem);
        return orderItem.getSubTotal();
    }).reduce(Money.ZERO, Money::add);

   if(price.equals(orderItemsTotal)){
       throw new OrderDomainException("Total price: " + price.getAmount() + "is not equal to order Items Total:" + orderItemsTotal.getAmount());
   }
    }

    private void validateItemsPrice(OrderItem orderItem) {
    if(!orderItem.isPriceValid())
        throw new OrderDomainException("Total price: " + orderItem.getPrice().getAmount() + "is not valid for product:" + orderItem.getProduct().getPrice().getAmount());
    }
    public static Builder builder() {
        return new Builder();
    }
    private void validateTotalPrice() {
        if(price==null || !price.isGreaterThanZero())
        {
            throw new OrderDomainException("Total price should be greater than zero");

        }
    }

    private void validateInitialOrder() {
        if(orderStatus!=null || getId() !=null){
            throw new OrderDomainException(" Order is not in correct state for initialization");
        }
    }

    private void initializeOrderItems() {
    long itemId=1;
    for(OrderItem orderItem:items)
    {
        orderItem.initializeOrderItem(super.getId(),new OrderItemId(itemId++));
    }
    }

    private Order(Builder builder) {
        super.setId(builder.id);
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessage = builder.failureMessage;
        customerId = builder.customerId;
    }

    public static final class Builder {
        private OrderId id;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessage;
        private CustomerId customerId;

        private Builder() {
        }



        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessage(List<String> val) {
            failureMessage = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
