package org.food.ordering.system.order.service.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderAddress {

    @NotNull
    private final String street;
    private final String postalCode;
    private final String city;
}
