package com.example.__words_ielts.model.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @NotBlank(message = "EMAIL_INVALID")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PASSWORD_NOT_BLANK")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "PASSWORD_INVALID"
    )
    private String password;
}
