package org.food.ordering.system.order.service.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter @Builder @AllArgsConstructor
public class TrackOrderQuery {
    private final UUID orderTrackingId;
}
