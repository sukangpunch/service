package com.delivery.api.doamin.token.Interface;

import com.delivery.api.doamin.token.model.TokenDto;

import java.util.Map;

public interface TokenHelperInterface {
    TokenDto issueAccessToken(Map<String, Object> data);

    TokenDto issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
