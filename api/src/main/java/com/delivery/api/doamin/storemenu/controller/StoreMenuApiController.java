package com.delivery.api.doamin.storemenu.controller;

import com.delivery.api.common.api.Api;
import com.delivery.api.doamin.storemenu.bussiness.StoreMenuBusiness;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    // storeId 에 해당하는 메뉴 search
    // 사용자가 클릭해서 받는 파라미터 : /search?storeId = 1
    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(
            @RequestParam("storeId") Long storeId
    ){
        var response = storeMenuBusiness.search(storeId);
        return Api.OK(response);
    }
}
