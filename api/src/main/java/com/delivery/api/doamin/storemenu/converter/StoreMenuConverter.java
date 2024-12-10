package com.delivery.api.doamin.storemenu.converter;

import com.delivery.api.common.anotation.Converter;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.api.doamin.storemenu.controller.model.StoreMenuResponse;
import com.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;

@Converter
public class StoreMenuConverter {

    // toEntity
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it -> {

                    return StoreMenuEntity.builder()
                            .storeId(request.getStoreId())
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // toResponse
    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity){
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getId())
                            .storeId(storeMenuEntity.getStoreId())
                            .name(storeMenuEntity.getName())
                            .amount(storeMenuEntity.getAmount())
                            .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                            .status(storeMenuEntity.getStatus())
                            .likeCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // entity list -> response list 로 변환해주는 toResponse
    public List<StoreMenuResponse> toResponse(
            List<StoreMenuEntity> list
    ){
        return list.stream()
                .map(this::toResponse)
                .toList();
    }
}
