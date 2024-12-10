package com.delivery.api.doamin.token.converter;

import com.delivery.api.common.anotation.Converter;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.token.controller.model.TokenResponse;
import com.delivery.api.doamin.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toTokenResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        // null 체크
        Objects.requireNonNull(accessToken,()-> {throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken,()-> {throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
