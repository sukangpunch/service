package com.delivery.api.config.web;

import com.delivery.api.interceptor.AuthorizationInterceptor;
import com.delivery.api.resolver.UserSessionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// Web MVC 설정
// WebMvcConfigurer 를 구현하여 요청 처리와 관련된 동작을 정의
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;
    private final UserSessionResolver userSessionResolver;

    /*
        * 'open-api' 로 시작하는 url은 검증하지 않음
        *  아래 리스트들 전부 인터셉터 적용이 제외된다.
     */

    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"

    );

    // 인터셉터를 등록하고 URL 패턴에 따라 적용 범위를 설정
    // 컨트롤러에 요청이 도달하기 전 또는 후에 특정 로직을 실행할 수 있는 메커니즘
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER);
    }

    // addArgumentResolvers 오버라이드
    // 요청 핸들러 메서드(컨트롤러 메서드) 의 매개변수를 처리할 커스텀 HandlerMethodArgumentResolver 를 등록
    // UserSeesionResolver 가 등록되어 특정 매개변수를 자동으로 처리
    // 컨트롤러 메서드에서 @UserSession 어노테이션을 붙인 매개변수는 Spring이 알아서 세션에서 값을 추출하고 UserSession 객체를 생성하여 매개변수에 주입
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userSessionResolver);
    }

    // CORS 설정 추가
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔트포인트 허용
                .allowedOrigins("http://localhost:3000")  // react 앱 URL
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") //모든 헤더 허용
                .allowCredentials(true); // 쿠키 허용
    }
}
