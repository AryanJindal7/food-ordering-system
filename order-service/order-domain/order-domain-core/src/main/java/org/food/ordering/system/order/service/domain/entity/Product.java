package org.food.ordering.system.order.service.domain.entity;

import lombok.Getter;
import org.food.ordering.system.domain.entity.BaseEntity;
import org.food.ordering.system.domain.valueObject.Money;
import org.food.ordering.system.domain.valueObject.ProductId;


@Getter
public class Product extends BaseEntity<ProductId> {
    public Product(ProductId productId,String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public Product(ProductId productId)
    {
        super.setId(productId);
    }
    private String name;
    private Money price;

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name=name;
        this.price=price;
    }
}
