package com.example.__words_ielts.model.dto.request.userLearnedCount;

import com.example.__words_ielts.enums.GameLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCountRequest {
    private Integer learnedWordCount;
    private GameLevelEnum gameImage;
    private GameLevelEnum gameListen;
    private GameLevelEnum gameSpeak;
    private GameLevelEnum gameWord;
    private GameLevelEnum gameListenAndWrite;
}
