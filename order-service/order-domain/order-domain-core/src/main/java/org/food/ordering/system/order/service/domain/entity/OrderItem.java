package org.food.ordering.system.order.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.food.ordering.system.domain.entity.BaseEntity;
import org.food.ordering.system.domain.valueObject.Money;
import org.food.ordering.system.domain.valueObject.OrderId;
import org.food.ordering.system.order.service.domain.valueObject.OrderItemId;

@Getter
//@Builder
public class OrderItem extends BaseEntity<OrderItemId> {
private OrderId orderId;
private final Product product;
private final int quantity;
private final Money price;
private final Money subTotal;

    void initializeOrderItem(OrderId orderId,OrderItemId orderItemId) {
        this.orderId=orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid(){
        return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.multiply(quantity).equals(subTotal);
    }
    private OrderItem(Builder builder) {
        super.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }


        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
