package com.example.__words_ielts.model.dto.response.word;

import com.example.__words_ielts.model.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordResponse {
    private Integer id;
    private String name;
    private String type;
    private String pronunciation;
    private String audio;
    private String definition;
    private String image;
    private Integer topicId;
    private Timestamp createAt;
    private Timestamp updateAt;

    public static WordResponse fromWord(Word word){
        return WordResponse.builder()
                .id(word.getId())
                .name(word.getName())
                .type(word.getType())
                .pronunciation(word.getPronunciation())
                .audio(word.getAudio())
                .definition(word.getDefinition())
                .image(word.getImage())
                .topicId(word.getTopic().getId())
                .createAt(word.getCreateAt())
                .updateAt(word.getUpdateAt())
                .build();
    }
}
