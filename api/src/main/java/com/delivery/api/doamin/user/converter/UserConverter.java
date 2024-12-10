package com.delivery.api.doamin.user.converter;

import com.delivery.api.common.anotation.Converter;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.user.controller.model.UserRegisterRequest;
import com.delivery.api.doamin.user.controller.model.UserResponse;
import com.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    // UserRegisterRequest => UserEntity
    public UserEntity toEntity(UserRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it -> {
                    // to entity
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                }).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"UserRegisterRequest is NULL"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it-> {
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                }).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }


}
