package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.request.user.*;
import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.dto.response.user.LoginResponse;
import com.example.__words_ielts.model.dto.response.user.UserResponse;
import com.example.__words_ielts.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    ApiResponse<LoginResponse> login(@RequestBody LoginRequest request){
        var result = userService.login(request);
        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/register")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        userService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @GetMapping
    public ApiResponse<Object> getAllUsers(
            @RequestParam(defaultValue = "email") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Email: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<Object>builder()
                .result(userService.getAllUsers(pageable, sort, direction))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PatchMapping("/changePassword")
    ApiResponse<UserResponse> changePassword(@RequestBody @Valid ChangePasswordRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.changePassword(request))
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<LoginResponse> login(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = userService.refreshToken(request);
        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .build();
    }
}
