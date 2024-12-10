package com.delivery.api.common.exception;

import com.delivery.api.common.error.ErrorCodeInterface;

// 룰을 정함, 해당 인터페이스를 상속 받으면, 두 필드가 필수적이다
public interface ApiExceptionInterface {
    ErrorCodeInterface getErrorCodeInterface();

    String getErrorDescription();
}
