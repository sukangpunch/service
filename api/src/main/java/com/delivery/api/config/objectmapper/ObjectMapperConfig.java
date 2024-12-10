package com.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    
    // ObjectMapper 는 Jackson 라이브러의 핵심 클래스
    // JSON 간의 변환 직렬화(JAVA -> JSON), 역직렬화(JSON->JAVA) 수행
    @Bean
    public ObjectMapper objectMapper(){

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module()); // jdk 8 버전 이후 클래스, stream, optional 객체 처리 가능
        objectMapper.registerModule(new JavaTimeModule()); // local date 처리
        
        // JSON 데이터에 Java 클래스에 정의되지 않은 필드가 포함되어 있어도 에러를 발생시키지 않고 무시
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); // true:  모르는 프로퍼티스를 발견했을 때 에러가 터짐
        // 필드가 없는 빈 객체를 JSON으로 변환할 때 에러를 발생시키지 않고, 빈 JSON {}으로 처리.
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false); // false : 비어있는 객체가 들어와도 예외 x
        //Java 날짜 및 시간 타입(LocalDate, LocalDateTime)을 JSON으로 변환할 때, 타임스탬프가 아닌 ISO-8601 문자열로 출력.
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.LowerCamelCaseStrategy());
        
        return objectMapper;
    }
}
