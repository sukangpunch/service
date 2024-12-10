package com.delivery.api.doamin.token.business;

import com.delivery.api.common.anotation.Business;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.token.controller.model.TokenResponse;
import com.delivery.api.doamin.token.converter.TokenConverter;
import com.delivery.api.doamin.token.service.TokenService;
import com.delivery.api.doamin.user.controller.model.UserResponse;
import com.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity에서 user id 호출
     * 2. access, refresh token 발행 -> tokenService에 요청
     * 3. 발행한 token을 client로 response 하기 위한 token response로 변경 => token converter
     * 4. return
     */

    public TokenResponse issueToken(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(UserEntity::getId).map(userId->{
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toTokenResponse(accessToken,refreshToken);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken){
        var userId = tokenService.validationToken(accessToken);

        return userId;
    }

}
