package com.delivery.api.resolver;

import com.delivery.api.common.anotation.UserSession;
import com.delivery.api.doamin.user.model.User;
import com.delivery.api.doamin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


// 컨트롤러 메서드에서 특정 매개변수를 자동으로 처리하는 역할
// 주로 요청 컨텍스트(Request Context) 에 저장된 사용자 정보를 기반으로 User 객체를 생성하고 주입하는데 사용.
@RequiredArgsConstructor
@Component
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    // 컨트롤러 메서드의 매개변수가 이 리졸버에서 처리할 수 있는지 여부 체크
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크하는 영역

        // 1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 2. 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    // 매개변수 값 생성 메서드
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter에서 true 반환 시 여기 실행

        // parameter가 @UserSesson User user 이면
        // request context holder에서 user 정보 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();

        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // 사용자 정보 세팅
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
