package org.food.ordering.system.order.service;

import lombok.extern.slf4j.Slf4j;
import org.food.ordering.system.order.service.track.TrackOrderQuery;
import org.food.ordering.system.order.service.track.TrackOrderResponseClass;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderTrackCommandHandler {

    TrackOrderResponseClass trackOrderResponseClass(TrackOrderQuery trackOrderQuery){
        return null;
    }
}
