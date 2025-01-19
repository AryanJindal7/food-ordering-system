package org.food.ordering.system.order.service.domain.exception;

import org.food.ordering.system.domain.exception.DomainException;

import java.util.function.Supplier;


public class OrderDomainException extends DomainException {


    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }


}
