package com.delivery.api.doamin.store.controller.model;

import com.delivery.db.store.enums.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    private String name;
    @NotBlank //문자열 체크, null, "", "  " 체크 
    private String address;
    @NotNull // null 만 체크
    private StoreCategory category;
    @NotBlank
    private String thumbnailUrl;
    @NotNull
    private BigDecimal minimumAmount;
    @NotNull
    private BigDecimal minimumDeliveryAmount;
    @NotBlank
    private String phoneNumber;
}
