package com.example.__words_ielts.model.dto.request.userLearnedWords;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddUserLearnedWordsRequest {
    private Integer wordId;
}
