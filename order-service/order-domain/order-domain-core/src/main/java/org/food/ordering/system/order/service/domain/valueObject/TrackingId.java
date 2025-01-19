package org.food.ordering.system.order.service.domain.valueObject;

import org.food.ordering.system.domain.valueObject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
     public TrackingId(UUID value) {
        super(value);
    }
}
