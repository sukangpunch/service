package com.delivery.api.doamin.userorder.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {
    
    // 주문 : 특정 사용자가, 특정 메뉴를 주문
    // 특정 사용자 = 로그인한 세션에 들어있는 사용자
    // 특정 메뉴 id 리스트
    @NotNull
    private List<Long> storemenuIdList;

}
