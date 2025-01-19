package org.food.ordering.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import org.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import org.food.ordering.system.order.service.domain.entity.Customer;
import org.food.ordering.system.order.service.domain.entity.Order;
import org.food.ordering.system.order.service.domain.entity.Restaurant;
import org.food.ordering.system.order.service.domain.event.OrderCreateEvent;
import org.food.ordering.system.order.service.domain.exception.OrderDomainException;
import org.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import org.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import org.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import org.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {


    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService, OrderRepository orderRepository, CustomerRepository customerRepository, RestaurantRepository restaurantRepository, OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }


    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerID());
        Restaurant restaurant=checkRestaurant(createOrderCommand);
        Order order=orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreateEvent orderCreateEvent=orderDomainService.validateAndInitiateOrder(order,restaurant);
        Order orderResult=saveOrder(order);
        return orderDataMapper.orderToCreateOrderResponse(orderResult);

    }


    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {

        Restaurant restaurant=orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
     return  restaurantRepository.findRestaurantInformation(restaurant).orElseThrow(()->new OrderDomainException("Could not find restaurant with restaurant id: " + restaurant));
    }

    private void checkCustomer(UUID customerID) {
        Optional<Customer> customer=customerRepository.findCustomer(customerID);

        if(customer.isEmpty())
        {
            log.warn("Could not find customer with customer id: {}",customerID);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private Order saveOrder(Order order){
        Order orderResult=orderRepository.save(order);
        if(orderResult==null)
        {
            throw new OrderDomainException("Could not save order " + orderResult);
        }
        return orderResult;
    }
}
