package com.delivery.api.common.error;

public interface ErrorCodeInterface {
    Integer getHttpStatusCode(); // http 프로토콜 상태
    Integer getErrorCode(); // 직접 지정한 에러 코드, 비즈니스 로직 또는 도메인 관련 에러를 구체적으로 구분
    String getDescription(); // 에러 설명
}
