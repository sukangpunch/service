package com.delivery.api.doamin.user.business;

import com.delivery.api.common.anotation.Business;
import com.delivery.api.doamin.token.business.TokenBusiness;
import com.delivery.api.doamin.token.controller.model.TokenResponse;
import com.delivery.api.doamin.user.controller.model.UserLoginRequest;
import com.delivery.api.doamin.user.controller.model.UserRegisterRequest;
import com.delivery.api.doamin.user.controller.model.UserResponse;
import com.delivery.api.doamin.user.converter.UserConverter;
import com.delivery.api.doamin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입 처리 로직
     * 1. requestDto -> entity (converter에서 사용)
     * 2. entity를 save한다. (service 에서 사용)
     * 3. save entity를 다시  response(dto로) (converter에서 사용)
     * 4. response를 리턴
     */

    public UserResponse register(@Valid UserRegisterRequest request){
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }


    /*
     * 1. email, password 를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성 => 일단은 코드로 확인
     * 4. token response
     */

    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        // TODO: 토큰 생성
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    public UserResponse me(Long userId){
        var userEntity = userService.getUserWithThrow(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
