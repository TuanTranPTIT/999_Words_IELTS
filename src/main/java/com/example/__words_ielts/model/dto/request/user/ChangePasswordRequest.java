package com.example.__words_ielts.model.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank(message = "PASSWORD_NOT_BLANK")
    private String oldPassword;

    @NotBlank(message = "PASSWORD_NOT_BLANK")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "PASSWORD_INVALID"
    )
    private String newPassword;
}
