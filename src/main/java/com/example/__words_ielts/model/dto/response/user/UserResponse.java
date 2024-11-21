package com.example.__words_ielts.model.dto.response.user;

import com.example.__words_ielts.enums.UserRoleEnum;
import com.example.__words_ielts.enums.UserStatusEnum;
import com.example.__words_ielts.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String email;
    private UserRoleEnum role;
    private UserStatusEnum status;
    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
