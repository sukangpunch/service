package com.delivery.api.doamin.userorder.controller.model;

import com.delivery.api.doamin.store.controller.model.StoreResponse;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderDetailResponse {

    private UserOrderResponse userOrderResponse; //주문 정보

    private StoreResponse storeResponse; // store 정보

    private List<StoreMenuResponse> storeMenuResponseList; // menu 정보 리스트
}
