package com.example.__words_ielts.model.dto.response.userLearnedCount;

import com.example.__words_ielts.enums.GameLevelEnum;
import com.example.__words_ielts.model.entity.Topic;
import com.example.__words_ielts.model.entity.User;
import com.example.__words_ielts.model.entity.UserLearnedCount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLearnedCountResponse {
    private Integer id;
    private Integer userId;
    private Integer topicId;
    private Integer learnedWordCount = 0;
    private GameLevelEnum gameImage = GameLevelEnum.ZERO;
    private GameLevelEnum gameListen = GameLevelEnum.ZERO;
    private GameLevelEnum gameSpeak = GameLevelEnum.ZERO;
    private GameLevelEnum gameWord = GameLevelEnum.ZERO;
    private GameLevelEnum gameListenAndWrite = GameLevelEnum.ZERO;
    private Timestamp updatedAt;

    public static UserLearnedCountResponse fromUserLearnedCount(UserLearnedCount userLearnedCount){
        return UserLearnedCountResponse.builder()
                .id(userLearnedCount.getId())
                .userId(userLearnedCount.getUser().getId())
                .topicId(userLearnedCount.getTopic().getId())
                .learnedWordCount(userLearnedCount.getLearnedWordCount())
                .gameImage(userLearnedCount.getGameImage())
                .gameListen(userLearnedCount.getGameListen())
                .gameSpeak(userLearnedCount.getGameSpeak())
                .gameWord(userLearnedCount.getGameWord())
                .gameListenAndWrite(userLearnedCount.getGameListenAndWrite())
                .updatedAt(userLearnedCount.getUpdatedAt())
                .build();
    }
}
