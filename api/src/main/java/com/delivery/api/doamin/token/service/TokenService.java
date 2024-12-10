package com.delivery.api.doamin.token.service;

import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.token.Interface.TokenHelperInterface;
import com.delivery.api.doamin.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperInterface tokenHelperInterface;

    // access token 발행
    public TokenDto issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperInterface.issueAccessToken(data);
    }

    // refresh token 발행
    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperInterface.issueAccessToken(data);
    }
    // token validation
    public Long validationToken(String token){
        var map = tokenHelperInterface.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, ()->{throw new ApiException(ErrorCode.NULL_POINT);});

        return Long.parseLong(userId.toString());
    }
}
