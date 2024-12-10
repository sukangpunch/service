package com.delivery.api.common.api;

import com.delivery.api.common.error.ErrorCodeInterface;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    // result, API 요청 처리 결과 상태를 나타낸다.
    private Result result;

    // body, API 응답의 실제 데이터를 포함하는 필드
    @JsonProperty("body")
    @Valid
    private T body;

    // 성공 응답을 생성하는 메서드
    public static <T> Api<T> OK(T data){
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }
    
    // 오류 상태 응답을 생성하는 메서드
    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result = result;
        return api;
    }

    // 타입의 오류 코드를 인자로 받아 실패 응답을 처리,(에러 메시지, 에러 코드, HTTP STATUS) 포함 하는 인터페이스
    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeInterface); // result 객체가 errorCodeInterface 를 사용
        return api;
    }

    // 예외까지 포함한 상세한 실패 응답 처리
    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeInterface, tx);
        return api;
    }

    // 추가 문자열을 받아 실패 응답을 처리
    public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, String descriptions){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeInterface, descriptions);
        return api;
    }

}