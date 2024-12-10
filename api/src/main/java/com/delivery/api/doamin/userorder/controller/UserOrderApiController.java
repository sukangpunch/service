package com.delivery.api.doamin.userorder.controller;

import com.delivery.api.common.anotation.UserSession;
import com.delivery.api.common.api.Api;
import com.delivery.api.doamin.user.model.User;
import com.delivery.api.doamin.userorder.business.UserOrderBusiness;
import com.delivery.api.doamin.userorder.controller.model.UserOrderDetailResponse;
import com.delivery.api.doamin.userorder.controller.model.UserOrderRequest;
import com.delivery.api.doamin.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    // 사용자 주문
    @PostMapping
    public Api<UserOrderResponse> userOrder(
            @Valid
            @RequestBody Api<UserOrderRequest> userOrderRequest,
            @UserSession User user
    ){
        var response = userOrderBusiness.userOrder(user, userOrderRequest.getBody());
        return Api.OK(response);
    }

    // 현재 진행 중인 주문 내역
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true) // swagger 에서 보여지지 않도록 설정
            @UserSession User user
    ){
        var response = userOrderBusiness.current(user);
        return Api.OK(response);
    }

    // 과거 주문 내역
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true) // swagger 에서 보여지지 않도록 설정
            @UserSession User user
    ){
        var response = userOrderBusiness.history(user);
        return Api.OK(response);
    }

    // 주문 1건에 대한 조회(주문 상세 조회)
    @GetMapping("/id/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true) // swagger 에서 보여지지 않도록 설정
            @UserSession User user,
            @PathVariable("orderId") Long orderId
    ){
        var response = userOrderBusiness.read(user,orderId);
        return Api.OK(response);
    }


}
