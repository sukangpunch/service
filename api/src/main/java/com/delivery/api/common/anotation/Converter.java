package com.delivery.api.common.anotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Service 어노테이션을 확장 하여 특정 역할을 가지는 Spring 빈을 명시적으로 표현하기 위해 사용
// 해당 클래스는 데이터 변환 역할을 한다.
@Target(ElementType.TYPE) // 클래스 레벨에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지되며, 리플렉션을 통해 어노테이션 정보를 읽을 수 있음
@Service
public @interface Converter {
    // Service 의 value 속성과 같은 동작을 하도록 설정
    @AliasFor(annotation = Service.class) 
    String value() default "";
}
