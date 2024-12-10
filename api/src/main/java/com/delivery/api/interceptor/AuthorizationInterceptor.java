package com.delivery.api.interceptor;

import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.error.TokenErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.doamin.token.business.TokenBusiness;
import com.delivery.api.doamin.token.service.TokenService;
import io.swagger.v3.oas.models.PathItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

// 인터셉터를 구현한 클래스, HTTP 요청이 컨트롤러에 도달하기 전에 요청을 가로채서 인증 토큰 검증 및 유성 검사 수행
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;
    @Override // 컨트롤러 메서드가 호출되기 전에 실행
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, (chrome과 같은 브라우저) 의 경우 GET, POST, OPTIONS = PASS
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }
        
        // js, html, png (정적 resource)를 요청하는 경우 = PASS
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // TODO: header 검증
        var accessToken = request.getHeader("authorization-token");
        if (accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUNT);
        }

        // 토큰에서 사용자 ID 추출
        var userId = tokenBusiness.validationAccessToken(accessToken);

        // 유효한 사용자 ID를 요청 컨텍스트에 저장
        if(userId != null){
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);

            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST);
    }

}
