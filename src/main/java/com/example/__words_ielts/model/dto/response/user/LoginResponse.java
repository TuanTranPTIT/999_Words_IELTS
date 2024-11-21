package com.example.__words_ielts.model.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private boolean authenticated;
    private String token;
//    private String refreshToken;
//    private String accessToken;
//    private String id;
//    private String email;
//    private UserStatusEnum status;
}
