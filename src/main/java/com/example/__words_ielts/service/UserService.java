package com.example.__words_ielts.service;

import com.example.__words_ielts.model.dto.request.user.*;
import com.example.__words_ielts.model.dto.response.user.IntrospectResponse;
import com.example.__words_ielts.model.dto.response.user.LoginResponse;
import com.example.__words_ielts.model.dto.response.user.UserResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    UserResponse createUser(UserCreateRequest userCreateRequest);
    Page<UserResponse> getAllUsers(Pageable pageable, String sort, String direction);
    UserResponse getMyInfo();
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    LoginResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    UserResponse changePassword(ChangePasswordRequest request);
}
