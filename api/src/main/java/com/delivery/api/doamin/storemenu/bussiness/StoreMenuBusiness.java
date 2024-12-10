package com.delivery.api.doamin.storemenu.bussiness;

import com.delivery.api.common.anotation.Business;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuResponse;
import com.delivery.api.doamin.storemenu.converter.StoreMenuConverter;
import com.delivery.api.doamin.storemenu.service.StoreMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
@Slf4j
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;

    private final StoreMenuConverter storeMenuConverter;

    // 가게 메뉴 등록
    public StoreMenuResponse register(
            StoreMenuRegisterRequest request
    ){
        log.info("storeId : {}", request.getStoreId());
        // req -> entity : save -> response
        var entity = storeMenuConverter.toEntity(request);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    // 특정 가게에 있는 메뉴 검색
    public List<StoreMenuResponse> search(
            Long storeId
    ){
        var list = storeMenuService.getStoreMenuByStoreId(storeId);

        // entity list -> response list 반환
        return list.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
