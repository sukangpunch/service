package com.delivery.api.doamin.storemenu.controller;

import com.delivery.api.common.api.Api;
import com.delivery.api.doamin.storemenu.bussiness.StoreMenuBusiness;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-menu")
@Slf4j
public class StoreMenuOpenApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> request
    ){
        var req = request.getBody();
        log.info("body : {}", req);
        log.info("storeId : {}, amount : {}, thumnail : {}", req.getStoreId(), req.getAmount(), req.getThumbnailUrl());
        var response = storeMenuBusiness.register(req);
        return Api.OK(response);
    }
}
