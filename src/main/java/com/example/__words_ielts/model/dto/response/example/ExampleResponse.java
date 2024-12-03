package com.example.__words_ielts.model.dto.response.example;

import com.example.__words_ielts.model.entity.Example;
import com.example.__words_ielts.model.entity.Word;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExampleResponse {
    private Integer id;
    private String name;
    private String audio;
    private Integer wordId;
    private Timestamp createAt;
    private Timestamp updateAt;

    public static ExampleResponse fromExample(Example example){
        return ExampleResponse.builder()
                .id(example.getId())
                .name(example.getName())
                .audio(example.getAudio())
                .wordId(example.getWord().getId())
                .createAt(example.getCreateAt())
                .updateAt(example.getUpdateAt())
                .build();
    }
}
