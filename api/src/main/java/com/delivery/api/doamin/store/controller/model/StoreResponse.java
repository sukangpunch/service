package com.delivery.api.doamin.store.controller.model;

import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private Long id;
    private String name;
    private String address;
    private StoreStatus status;
    private StoreCategory category;
    private Double star;
    private String thumbnailUrl;
    private BigDecimal minimumAmount;
    private BigDecimal minimumDeliveryAmount;
    private String phoneNumber;
}
