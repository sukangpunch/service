package com.delivery.api.doamin.user.controller;


import com.delivery.api.common.anotation.UserSession;
import com.delivery.api.common.api.Api;
import com.delivery.api.doamin.user.business.UserBusiness;
import com.delivery.api.doamin.user.controller.model.UserResponse;
import com.delivery.api.doamin.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    //         아래와 같은 동작을 @UserSession 을 통해 없애준다.
//        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
//        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
//        var response = userBusiness.me(Long.parseLong(userId.toString()));
    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
    ){
        var response = userBusiness.me(user.getId());
        return Api.OK(response);
    }
}
