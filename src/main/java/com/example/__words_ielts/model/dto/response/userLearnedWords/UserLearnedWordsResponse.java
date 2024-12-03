package com.example.__words_ielts.model.dto.response.userLearnedWords;

import com.example.__words_ielts.model.entity.User;
import com.example.__words_ielts.model.entity.UserLearnedWords;
import com.example.__words_ielts.model.entity.Word;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLearnedWordsResponse {
    private Integer id;
    private Integer userId;
    private Integer wordId;
    private Timestamp learnedAt;

    public static UserLearnedWordsResponse fromUserLearnedWords(UserLearnedWords userLearnedWords){
        return UserLearnedWordsResponse.builder()
                .id(userLearnedWords.getId())
                .userId(userLearnedWords.getUser().getId())
                .wordId(userLearnedWords.getWord().getId())
                .learnedAt(userLearnedWords.getLearnedAt())
                .build();
    }
}
