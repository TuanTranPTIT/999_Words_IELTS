package com.example.__words_ielts.model.entity;

import com.example.__words_ielts.enums.GameLevelEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user_learned_count")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLearnedCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_learned_count_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_learned_count_topic_id"))
    private Topic topic;

    @Column(name = "learned_word_count", nullable = false)
    private Integer learnedWordCount = 0;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_image", nullable = false)
    private GameLevelEnum gameImage = GameLevelEnum.ZERO;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_listen", nullable = false)
    private GameLevelEnum gameListen = GameLevelEnum.ZERO;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_speak", nullable = false)
    private GameLevelEnum gameSpeak = GameLevelEnum.ZERO;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_word", nullable = false)
    private GameLevelEnum gameWord = GameLevelEnum.ZERO;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_listen_and_write", nullable = false)
    private GameLevelEnum gameListenAndWrite = GameLevelEnum.ZERO;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
