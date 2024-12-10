package com.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// JPA 엔티티 클래스의 스캔 범위를 지정. 엔티티 클래스가 다른 패키지에 있으면 명시적으로 지정해야한다.
@EntityScan(basePackages = "com.delivery.db") // 여러개의 패키지에 정보를 넣고싶으면 배열 형식으로
// JPA 리포지토리 인터페이스를 스캔하여 Spring Data JPA 가 이를 구현체로 등록하도록 한다.
@EnableJpaRepositories(basePackages = "com.delivery.db")
public class JpaConfig {
}
